package com.bifos.corebanking.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Table
@Entity
class AccountHistory(
    account: Account,
    prevBalance: Long,
    currentBalance: Long,
    deltaBalance: Long,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    var account: Account = account
        protected set

    @Column
    var accountNumber: String = account.accountNumber
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