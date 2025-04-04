package com.bifos.corebanking.entity

import com.bifos.corebanking.exception.ExceptionInfo
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        protected set

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

    fun withdraw(balance: Long) {
        if (this.balance < balance) {
            throw ExceptionInfo.NOT_ENOUGH_BALANCE.exception()
        }
        this.balance -= balance
    }
}