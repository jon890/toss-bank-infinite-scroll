package com.bifos.transfer.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Table
@Entity
class TransferRequest(
    fromAccountNumber: String,
    toAccountNumber: String,
    balance: Long,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    val uuid: String = UUID.randomUUID().toString()

    @Column
    var fromAccountNumber: String = fromAccountNumber
        protected set

    @Column
    var toAccountNumber: String = toAccountNumber
        protected set

    @Column
    var balance: Long = balance
        protected set

    @Column
    @Enumerated(EnumType.STRING)
    var status: TransferStatus = TransferStatus.REQUESTED
        protected set

    @Column
    var requestedAt: LocalDateTime = LocalDateTime.now()
        protected set

    @Column
    var completedAt: LocalDateTime? = null
        protected set

    fun complete() {
        this.status = TransferStatus.COMPLETED
        this.completedAt = LocalDateTime.now()
    }

    fun fail() {
        this.status = TransferStatus.FAILED
        this.completedAt = LocalDateTime.now()
    }
}

enum class TransferStatus {
    REQUESTED,   // 요청됨
    COMPLETED,   // 완료됨
    FAILED       // 실패함
} 