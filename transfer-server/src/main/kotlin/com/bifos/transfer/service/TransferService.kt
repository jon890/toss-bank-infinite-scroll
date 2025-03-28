package com.bifos.transfer.service

import com.bifos.transfer.client.CoreBankingFeign
import com.bifos.transfer.client.dto.TransferExternalRequest
import com.bifos.transfer.client.dto.TransferStatusResponse
import com.bifos.transfer.entity.TransferRequest
import com.bifos.transfer.entity.TransferStatus
import com.bifos.transfer.repository.TransferRequestRepository
import com.bifos.transfer.service.dto.TransferCommand
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TransferService(
    private val coreBankingFeign: CoreBankingFeign,
    private val transferRequestRepository: TransferRequestRepository
) {

    companion object {
        private const val TRANSFER_TIMEOUT_MINUTES = 1L
    }

    @Transactional
    fun transfer(transferCommand: TransferCommand): String {
        // 이미 진행 중인 송금이 있는지 확인
        if (hasOngoingTransfer(transferCommand.fromAccountNumber)) {
            throw IllegalStateException("이미 진행 중인 송금이 있습니다.")
        }

        // 송금 요청 저장
        val transferRequest = TransferRequest(
            fromAccountNumber = transferCommand.fromAccountNumber,
            toAccountNumber = transferCommand.toAccountNumber,
            balance = transferCommand.balance
        )
        transferRequestRepository.save(transferRequest)

        try {
            // 코어뱅킹에 송금 요청
            coreBankingFeign.transfer(
                TransferExternalRequest(
                    transferId = transferRequest.uuid,
                    fromAccountNumber = transferCommand.fromAccountNumber,
                    toAccountNumber = transferCommand.toAccountNumber,
                    balance = transferCommand.balance,
                    requestedAt = transferRequest.requestedAt
                )
            )
            return transferRequest.uuid
        } catch (e: Exception) {
            // 예외 발생 시 요청 상태는 REQUESTED로 유지, 스케줄러에서 처리
            return transferRequest.uuid
        }
    }

    /**
     * 진행 중인 송금이 있는지 확인
     */
    private fun hasOngoingTransfer(accountNumber: String): Boolean {
        return transferRequestRepository.existsByFromAccountNumberAndStatus(accountNumber, TransferStatus.REQUESTED)
    }

    /**
     * 송금 상태 확인
     */
    @Transactional
    fun checkTransferStatus(transferId: String): TransferStatus {
        val transferRequest = transferRequestRepository.findByUuid(transferId)
            ?: throw IllegalArgumentException("송금 요청을 찾을 수 없습니다.")

        if (transferRequest.status != TransferStatus.REQUESTED) {
            return transferRequest.status
        }

        try {
            val response = coreBankingFeign.getTransferStatus(transferId)
            val statusResponse = response.body ?: return TransferStatus.REQUESTED

            when (statusResponse.status) {
                TransferStatusResponse.STATUS_SUCCESS -> {
                    transferRequest.complete()
                    return TransferStatus.COMPLETED
                }
                TransferStatusResponse.STATUS_FAIL -> {
                    transferRequest.fail()
                    return TransferStatus.FAILED
                }
                TransferStatusResponse.STATUS_NOT_FOUND -> {
                    // 타임아웃이 지났는지 확인
                    if (isTimedOut(transferRequest.requestedAt)) {
                        transferRequest.fail()
                        return TransferStatus.FAILED
                    }
                }
            }
        } catch (e: Exception) {
            // API 호출에 실패한 경우, 상태 변경하지 않음
        }

        return TransferStatus.REQUESTED
    }

    /**
     * 송금 요청이 타임아웃되었는지 확인
     */
    private fun isTimedOut(requestedAt: LocalDateTime): Boolean {
        val timeoutThreshold = LocalDateTime.now().minusMinutes(TRANSFER_TIMEOUT_MINUTES)
        return requestedAt.isBefore(timeoutThreshold)
    }

    /**
     * 주기적으로 미완료된 송금 요청들의 상태를 확인하는 스케줄러
     * 5초마다 실행
     */
    @Scheduled(fixedDelay = 5000)
    @Transactional
    fun checkPendingTransfers() {
        val timeoutThreshold = LocalDateTime.now().minusMinutes(TRANSFER_TIMEOUT_MINUTES)
        val pendingRequests = transferRequestRepository.findByStatusAndRequestedAtBefore(
            TransferStatus.REQUESTED, timeoutThreshold
        )

        for (request in pendingRequests) {
            try {
                val response = coreBankingFeign.getTransferStatus(request.uuid)
                val statusResponse = response.body

                when (statusResponse?.status) {
                    TransferStatusResponse.STATUS_SUCCESS -> request.complete()
                    TransferStatusResponse.STATUS_FAIL, TransferStatusResponse.STATUS_NOT_FOUND -> request.fail()
                }
            } catch (e: Exception) {
                // API 호출에 실패한 경우, 타임아웃으로 실패 처리
                request.fail()
            }
        }
    }
}