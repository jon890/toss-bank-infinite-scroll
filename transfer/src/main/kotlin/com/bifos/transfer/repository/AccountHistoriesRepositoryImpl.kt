package com.bifos.transfer.repository

import com.bifos.transfer.entity.AccountHistory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class AccountHistoriesRepositoryImpl(private val accountHistoryJpaRepository: AccountHistoryJpaRepository) :
    AccountHistoriesRepository {

    override fun findHistoriesByAccountNumber(
        accountNumber: String,
        baseTime: LocalDateTime,
        count: Int
    ): List<AccountHistory> {
        return accountHistoryJpaRepository.findHistoriesByAccountNumber(accountNumber, baseTime, count)
    }

    override fun findHistoriesByAccountId(
        accountId: String,
        baseTime: LocalDateTime,
        count: Int
    ): List<AccountHistory> {
        return accountHistoryJpaRepository.findHistoriesByAccountId(accountId, baseTime, count)
    }
}