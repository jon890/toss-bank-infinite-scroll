package com.bifos.corebanking.repository

import com.bifos.corebanking.entity.TransferRecord
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface TransferRecordRepository : JpaRepository<TransferRecord, Long> {
    fun findByTransferId(transferId: String): TransferRecord?
    
    fun existsByTransferId(transferId: String): Boolean
    
    fun findByRequestedAtBeforeAndTransferId(requestedAt: LocalDateTime, transferId: String): TransferRecord?
} 