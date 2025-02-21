package com.bifos.corebanking.repository

import com.bifos.corebanking.entity.AccountHistory

interface AccountHistoryRepository {
    fun createHistory(accountHistory: AccountHistory) : AccountHistory
}