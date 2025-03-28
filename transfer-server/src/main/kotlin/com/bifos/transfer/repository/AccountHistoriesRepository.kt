package com.bifos.transfer.repository

import com.bifos.transfer.entity.AccountHistory
import java.time.LocalDateTime

interface AccountHistoriesRepository {
    /**
     * accountId 를 기준으로
     * 기준 시간 이전 이체 내역을 조회한다.
     */
    fun findHistoriesByAccountId(accountId: String, baseTime: LocalDateTime, count: Int): List<AccountHistory>

    /**
     * accountNumber(계좌번호) 를 기준으로
     * 기준 시간 이전 이체 내역을 조회한다.
     */
    fun findHistoriesByAccountNumber(accountNumber: String, baseTime: LocalDateTime, count: Int): List<AccountHistory>
}