package com.bifos.corebanking.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.*

@Table(indexes = [Index(columnList = "account_number")])
@Entity
class Account(
    accountNumber: String,
    username: String,
    balance: Long
) {
    @Id
    val id: String = UUID.randomUUID().toString()

    /**
     * 계좌번호
     */
    @Column(unique = true, nullable = false)
    var accountNumber: String = accountNumber
        protected set

    @Column(nullable = false)
    val username: String = username

    @Column
    var balance: Long = balance

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now()
        protected set

    /**
     * 입금
     */
    fun deposit(balance: Long) {
        this.balance += balance
    }
}