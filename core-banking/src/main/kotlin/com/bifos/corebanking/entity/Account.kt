package com.bifos.corebanking.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.UUID

@Table
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
    val accountNumber: String = accountNumber

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
}