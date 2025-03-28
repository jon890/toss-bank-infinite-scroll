package com.bifos.corebanking.repository

import com.bifos.corebanking.entity.AccountHistory
import org.springframework.stereotype.Repository

@Repository
class AccountHistoryRepositoryImpl(private val accountHistoryJpaRepository: AccountHistoryJpaRepository) : AccountHistoryRepository {

    override fun createHistory(accountHistory: AccountHistory): AccountHistory {
        return accountHistoryJpaRepository.save(accountHistory)
    }
}