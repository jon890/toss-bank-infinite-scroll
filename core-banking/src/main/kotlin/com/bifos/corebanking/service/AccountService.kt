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
    @Transactional(transactionManager = "transactionManager")
    fun createAccount(command: CreateAccountCommand): Account {
        return accountRepository.createAccount(command.username, AccountNumberGenerator.generate())
    }

    fun findByAccountNumberForUpdateWithThrows(accountNumber: String): Account {
        return accountRepository.findByAccountNumberForUpdate(accountNumber)
            ?: throw ExceptionInfo.NOT_FOUND_ACCOUNT.exception()
    }

    @Transactional(transactionManager = "transactionManager")
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
     *  spring.kafka.producer.transaction-id-prefix 를 추가하여 kafkaTransactionManager 를 사용하여
     *  DB 트랜잭션 이후에 메시지가 발행되도록 한다
     */
    @Transactional(transactionManager = "transactionManager")
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

    @Transactional(transactionManager = "transactionManager")
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