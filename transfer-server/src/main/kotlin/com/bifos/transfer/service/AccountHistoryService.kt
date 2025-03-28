package com.bifos.transfer.service

import com.bifos.transfer.entity.AccountHistory
import com.bifos.transfer.repository.AccountHistoriesRepository
import com.bifos.transfer.service.dto.FindAccountHistoryCommand
import org.springframework.stereotype.Service

@Service
class AccountHistoryService(
    private val accountHistoriesRepository: AccountHistoriesRepository
) {
    fun getHistories(command: FindAccountHistoryCommand): List<AccountHistory> {
        return accountHistoriesRepository.findHistoriesByAccountNumber(
            command.accountNumber,
            command.baseTime,
            command.count
        )
    }
}