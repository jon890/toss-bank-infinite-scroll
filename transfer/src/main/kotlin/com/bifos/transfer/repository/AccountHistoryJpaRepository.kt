package com.bifos.transfer.repository

import com.bifos.transfer.entity.AccountHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface AccountHistoryJpaRepository : JpaRepository<AccountHistory, Long> {

    @Query("select ach " +
            "from AccountHistory ach " +
            "where ach.id = :accountId and " +
            "ach.createdAt <= :createdAt " +
            "order by ach.createdAt desc " +
            "limit :count")
    fun findHistoriesByAccountId(accountId: String, createdAt: LocalDateTime, count : Int): List<AccountHistory>

    @Query("select ach " +
            "from AccountHistory ach " +
            "where ach.accountNumber = :accountNumber and " +
            "ach.createdAt <= :createdAt " +
            "order by ach.createdAt desc " +
            "limit :count")
    fun findHistoriesByAccountNumber(accountNumber: String, createdAt: LocalDateTime, count : Int): List<AccountHistory>

}