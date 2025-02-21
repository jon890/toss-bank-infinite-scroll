package com.bifos.transfer.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import java.util.*

@Table
@Entity
class AccountHistory(
    prevBalance: Long,
    currentBalance: Long,
    deltaBalance: Long,
    accountNumber: String,
    accountId: String
) {
    @Id
    val id: String = UUID.randomUUID().toString()

    @Column(nullable = false)
    var accountId: String = accountId
        protected set

    /**
     * 계좌 번호
     */
    @Column(nullable = false)
    var accountNumber: String = accountNumber
        protected set

    @Column(nullable = false)
    var prevBalance: Long = prevBalance
        protected set

    @Column(nullable = false)
    var currentBalance: Long = currentBalance
        protected set

    @Column(nullable = false)
    var deltaBalance: Long = deltaBalance
        protected set

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set
}