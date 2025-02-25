package com.bifos.corebanking.service

import com.bifos.corebanking.entity.Account
import com.bifos.corebanking.entity.AccountHistory
import com.bifos.corebanking.exception.ExceptionInfo
import com.bifos.corebanking.repository.AccountRepository
import com.bifos.corebanking.service.dto.*
import com.bifos.corebanking.util.AccountNumberGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val accountHistoryService: AccountHistoryService
) {
    @Transactional
    fun createAccount(command: CreateAccountCommand): Account {
        return accountRepository.createAccount(command.username, AccountNumberGenerator.generate())
    }

    fun findByAccountNumberForUpdateWithThrows(accountNumber: String): Account {
        return accountRepository.findByAccountNumberForUpdate(accountNumber)
            ?: throw ExceptionInfo.NOT_FOUND_ACCOUNT.exception()
    }

    @Transactional
    fun deposit(command: DepositCommand): DepositResult {
        val account = findByAccountNumberForUpdateWithThrows(command.accountNumber)

        val prevBalance = account.balance
        val deltaBalance = command.balance
        account.deposit(deltaBalance)
        val afterBalance = account.balance

        accountHistoryService.createAccountHistory(
            CreateAccountHistoryCommand(
                accountNumber = account.accountNumber,
                prevBalance = prevBalance,
                deltaBalance = deltaBalance,
                currentBalance = afterBalance,
            )
        )

        return DepositResult(account.accountNumber, prevBalance, afterBalance, deltaBalance)
    }

    /**
     *  todo 메시지 전송 후, 예외 발생하여 DB 롤백되는 경우는 어떻게 되는가?
     *  kafka 에서 해당 메시지를 버리라고 해야할까?
     *  아니면 메시지 전송도 한 트랜잭션 단위로 묶어낼 수 있을까?
     */
    @Transactional
    fun depositWithThrows(command: DepositCommand): DepositResult {
        val account = findByAccountNumberForUpdateWithThrows(command.accountNumber)

        val prevBalance = account.balance
        val deltaBalance = command.balance
        account.deposit(deltaBalance)
        val afterBalance = account.balance

        accountHistoryService.createAccountHistory(
            CreateAccountHistoryCommand(
                accountNumber = account.accountNumber,
                prevBalance = prevBalance,
                deltaBalance = deltaBalance,
                currentBalance = afterBalance,
            )
        )

        throw ExceptionInfo.UNKNOWN.exception()

        return DepositResult(account.accountNumber, prevBalance, afterBalance, deltaBalance)
    }

    @Transactional
    fun transfer(command: TransferCommand) {
        val fromAccount = findByAccountNumberForUpdateWithThrows(command.fromAccountNumber)
        val toAccount = findByAccountNumberForUpdateWithThrows(command.toAccountNumber)

        val deltaBalance = command.balance

        // from 차감
        val fromPrevBalance = fromAccount.balance
        fromAccount.withdraw(deltaBalance)
        val fromAfterBalance = fromAccount.balance

        // from history
        accountHistoryService.createAccountHistory(
            CreateAccountHistoryCommand(
                accountNumber = fromAccount.accountNumber,
                prevBalance = fromPrevBalance,
                deltaBalance = deltaBalance * -1,
                currentBalance = fromAfterBalance,
            )
        )

        // to 증감
        val toPrevBalance = toAccount.balance
        toAccount.deposit(deltaBalance)
        val toAfterBalance = toAccount.balance

        // to history
        accountHistoryService.createAccountHistory(
            CreateAccountHistoryCommand(
                accountNumber = toAccount.accountNumber,
                prevBalance = toPrevBalance,
                deltaBalance = deltaBalance,
                currentBalance = toAfterBalance,
            )
        )
    }
}