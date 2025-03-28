package com.bifos.corebanking.service

import com.bifos.corebanking.entity.TransferRecord
import com.bifos.corebanking.repository.TransferRecordRepository
import com.bifos.corebanking.service.dto.TransferRecordCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TransferRecordService(private val transferRecordRepository: TransferRecordRepository) {

    companion object {
        private const val TRANSFER_TIMEOUT_MINUTES = 1L
    }

    /**
     * 송금 기록 저장
     */
    @Transactional
    fun saveTransferRecord(command: TransferRecordCommand): TransferRecord {
        val transferRecord = TransferRecord(
            transferId = command.transferId,
            fromAccountNumber = command.fromAccountNumber,
            toAccountNumber = command.toAccountNumber,
            balance = command.balance,
            requestedAt = command.requestedAt
        )
        return transferRecordRepository.save(transferRecord)
    }

    /**
     * 송금 완료 처리
     */
    @Transactional
    fun completeTransfer(transferId: String) {
        val transferRecord = findByTransferId(transferId)
            ?: throw IllegalArgumentException("Transfer record not found")
        transferRecord.complete()
        transferRecordRepository.save(transferRecord)
    }

    /**
     * 송금 실패 처리
     */
    @Transactional
    fun failTransfer(transferId: String) {
        val transferRecord = findByTransferId(transferId)
            ?: throw IllegalArgumentException("Transfer record not found")
        transferRecord.fail()
        transferRecordRepository.save(transferRecord)
    }

    /**
     * 송금 기록 조회
     */
    fun findByTransferId(transferId: String): TransferRecord? {
        return transferRecordRepository.findByTransferId(transferId)
    }

    /**
     * 송금 요청 존재 여부 확인
     */
    fun existsByTransferId(transferId: String): Boolean {
        return transferRecordRepository.existsByTransferId(transferId)
    }

    /**
     * 송금 요청 타임아웃 여부 확인
     */
    fun isRequestTimedOut(requestedAt: LocalDateTime): Boolean {
        val timeoutThreshold = LocalDateTime.now().minusMinutes(TRANSFER_TIMEOUT_MINUTES)
        return requestedAt.isBefore(timeoutThreshold)
    }
} 