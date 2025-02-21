package com.bifos.corebanking.service

import com.bifos.corebanking.entity.Account
import com.bifos.corebanking.entity.AccountHistory
import com.bifos.corebanking.exception.ExceptionInfo
import com.bifos.corebanking.repository.AccountHistoryRepository
import com.bifos.corebanking.repository.AccountRepository
import com.bifos.corebanking.service.dto.AccountHistoryProduceCommand
import com.bifos.corebanking.service.dto.CreateAccountCommand
import com.bifos.corebanking.service.dto.DepositCommand
import com.bifos.corebanking.service.dto.DepositResult
import com.bifos.corebanking.util.AccountNumberGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val accountHistoryRepository: AccountHistoryRepository,
    private val accountHistoryProducer: AccountHistoryProducer
) {
    @Transactional
    fun createAccount(command: CreateAccountCommand): Account {
        return accountRepository.createAccount(command.username, AccountNumberGenerator.generate())
    }

    @Transactional
    fun deposit(command: DepositCommand): DepositResult {
        val account = accountRepository.findByAccountNumberForUpdate(command.accountNumber)
                ?: throw ExceptionInfo.NOT_FOUND_ACCOUNT.exception()

        val prevBalance = account.balance
        val deltaBalance = command.balance
        account.deposit(deltaBalance)
        val afterBalance = account.balance

        val accountHistory = AccountHistory(prevBalance, afterBalance, deltaBalance, account)
        val createHistory = accountHistoryRepository.createHistory(accountHistory)
        accountHistoryProducer.produce(AccountHistoryProduceCommand(
            accountNumber = account.accountNumber,
            prevBalance = prevBalance,
            deltaBalance = deltaBalance,
            currentBalance = afterBalance,
            createdAt = createHistory.createdAt
        ))

        return DepositResult(account.accountNumber, prevBalance, afterBalance, deltaBalance)
    }
}