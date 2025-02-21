package com.bifos.corebanking.service

import com.bifos.corebanking.entity.Account
import com.bifos.corebanking.exception.ExceptionInfo
import com.bifos.corebanking.repository.AccountRepository
import com.bifos.corebanking.service.dto.CreateAccountCommand
import com.bifos.corebanking.service.dto.DepositCommand
import com.bifos.corebanking.service.dto.DepositResult
import com.bifos.corebanking.util.AccountNumberGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val accountRepository: AccountRepository
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
        account.deposit(command.balance)
        val afterBalance = account.balance

        return DepositResult(account.accountNumber, prevBalance, afterBalance, command.balance)
    }
}