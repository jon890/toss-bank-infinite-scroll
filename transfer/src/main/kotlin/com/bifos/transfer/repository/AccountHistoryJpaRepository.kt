package com.bifos.transfer.repository

import com.bifos.transfer.entity.AccountHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface AccountHistoryJpaRepository : JpaRepository<AccountHistory, String> {

    @Query("select ach " +
            "from AccountHistory ach " +
            "where ach.id = :accountId and " +
            "ach.createdAt <= :createDate " +
            "order by ach.createdAt desc " +
            "limit :count")
    fun findHistoriesByAccountId(accountId: String, createdDate: LocalDateTime, count : Int): List<AccountHistory>

    @Query("select ach " +
            "from AccountHistory ach " +
            "where ach.id = :accountNumber and " +
            "ach.createdAt <= :createDate " +
            "order by ach.createdAt desc " +
            "limit :count")
    fun findHistoriesByAccountNumber(accountNumber: String, createdDate: LocalDateTime, count : Int): List<AccountHistory>

}