package com.bifos.transfer.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import java.util.*

@Table
@Entity
class AccountHistory(
    accountNumber: String,
    prevBalance: Long,
    currentBalance: Long,
    deltaBalance: Long,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    val uuid: String = UUID.randomUUID().toString()

    @Column
    var accountNumber: String = accountNumber
        protected set

    @Column
    var prevBalance: Long = prevBalance
        protected set

    @Column
    var currentBalance: Long = currentBalance
        protected set

    @Column
    var deltaBalance: Long = deltaBalance
        protected set

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set
}