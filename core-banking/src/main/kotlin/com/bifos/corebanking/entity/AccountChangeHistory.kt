package com.bifos.corebanking.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import java.util.*

@Table
@Entity
class AccountChangeHistory(
    prevBalance: Long,
    currentBalance: Long,
    deltaBalance: Long,
    account: Account
) {
    @Id
    val id: UUID = UUID.randomUUID()

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id")
    var account: Account = account
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