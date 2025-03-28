package com.bifos.corebanking.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Table
@Entity
class TransferRecord(
    transferId: String,
    fromAccountNumber: String,
    toAccountNumber: String,
    balance: Long,
    requestedAt: LocalDateTime,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column
    var transferId: String = transferId
        protected set

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
    var requestedAt: LocalDateTime = requestedAt
        protected set

    @Column
    var processedAt: LocalDateTime? = null
        protected set

    @Column
    @Enumerated(EnumType.STRING)
    var status: TransferStatus = TransferStatus.PROCESSING
        protected set

    fun complete() {
        this.status = TransferStatus.COMPLETED
        this.processedAt = LocalDateTime.now()
    }

    fun fail() {
        this.status = TransferStatus.FAILED
        this.processedAt = LocalDateTime.now()
    }
}

enum class TransferStatus {
    PROCESSING,   // 처리 중
    COMPLETED,    // 완료됨
    FAILED        // 실패함
} 