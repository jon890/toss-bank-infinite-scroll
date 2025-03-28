package com.bifos.transfer.repository

import com.bifos.transfer.entity.TransferRequest
import com.bifos.transfer.entity.TransferStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface TransferRequestRepository : JpaRepository<TransferRequest, Long> {

    @Query("SELECT tr FROM TransferRequest tr WHERE tr.fromAccountNumber = :accountNumber AND tr.status = :status")
    fun findByFromAccountNumberAndStatus(accountNumber: String, status: TransferStatus): List<TransferRequest>

    @Query("SELECT COUNT(tr) > 0 FROM TransferRequest tr WHERE tr.fromAccountNumber = :accountNumber AND tr.status = :status")
    fun existsByFromAccountNumberAndStatus(accountNumber: String, status: TransferStatus): Boolean
    
    @Query("SELECT tr FROM TransferRequest tr WHERE tr.status = :status AND tr.requestedAt < :timeThreshold")
    fun findByStatusAndRequestedAtBefore(status: TransferStatus, timeThreshold: LocalDateTime): List<TransferRequest>
    
    fun findByUuid(uuid: String): TransferRequest?
} 