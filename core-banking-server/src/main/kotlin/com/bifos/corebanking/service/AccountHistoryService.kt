package com.bifos.corebanking.service

import com.bifos.corebanking.entity.AccountHistory
import com.bifos.corebanking.repository.AccountHistoryRepository
import com.bifos.corebanking.service.dto.AccountHistoryProduceCommand
import com.bifos.corebanking.service.dto.CreateAccountHistoryCommand
import org.springframework.stereotype.Service

@Service
class AccountHistoryService(
    private val accountHistoryRepository: AccountHistoryRepository,
    private val accountHistoryProducer: AccountHistoryProducer
) {

    fun createAccountHistory(command: CreateAccountHistoryCommand) {
        val accountHistory = AccountHistory(
            command.accountNumber,
            command.prevBalance,
            command.currentBalance,
            command.deltaBalance
        )
        val createHistory = accountHistoryRepository.createHistory(accountHistory)

        accountHistoryProducer.produce(
            AccountHistoryProduceCommand(
                accountNumber = command.accountNumber,
                prevBalance = command.prevBalance,
                deltaBalance = command.deltaBalance,
                currentBalance = command.currentBalance,
                createdAt =  createHistory.createdAt
            )
        )
    }
}