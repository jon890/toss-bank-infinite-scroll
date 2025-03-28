package com.bifos.corebanking.web

import com.bifos.corebanking.service.AccountService
import com.bifos.corebanking.service.TransferRecordService
import com.bifos.corebanking.service.dto.CreateAccountCommand
import com.bifos.corebanking.service.dto.DepositCommand
import com.bifos.corebanking.service.dto.TransferCommand
import com.bifos.corebanking.service.dto.TransferRecordCommand
import com.bifos.corebanking.web.dto.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val accountService: AccountService,
    private val transferRecordService: TransferRecordService
) {

    /**
     * 계좌 생성
     */
    @PostMapping("")
    fun createAccount(@RequestBody createAccountRequest: CreateAccountRequest): ResponseEntity<*> {
        val createAccount =
            accountService.createAccount(CreateAccountCommand(createAccountRequest.username))
        return ResponseEntity.ok(createAccount)
    }

    /**
     * 타행에서 입금 받을 때, 사용되는 API
     */
    @PutMapping("/deposit")
    fun deposit(@RequestBody depositRequest: DepositRequest): ResponseEntity<*> {
        val depositResult = accountService.deposit(
            DepositCommand(
                depositRequest.accountNumber,
                depositRequest.balance
            )
        )
        return ResponseEntity.ok(depositResult)
    }

    /**
     * 타행입금 마지막에서 오류 테스트 API
     */
    @PutMapping("/deposit-with-throws")
    fun depositWithThrows(@RequestBody depositRequest: DepositRequest): ResponseEntity<*> {
        val depositResult = accountService.depositWithThrows(
            DepositCommand(
                depositRequest.accountNumber,
                depositRequest.balance
            )
        )
        return ResponseEntity.ok(depositResult)
    }

    /**
     * 이체, 타임아웃 처리를 포함한다.
     */
    @PutMapping("/transfer")
    fun transfer(@RequestBody transferRequest: TransferRequest): ResponseEntity<*> {
        // 타임아웃 체크
        if (transferRecordService.isRequestTimedOut(transferRequest.requestedAt)) {
            return ResponseEntity.badRequest().body("Request timed out")
        }
        
        // 중복 요청 체크
        if (transferRecordService.existsByTransferId(transferRequest.transferId)) {
            return ResponseEntity.badRequest().body("Duplicate transfer request")
        }
        
        // 송금 기록 저장
        transferRecordService.saveTransferRecord(
            TransferRecordCommand(
                transferId = transferRequest.transferId,
                fromAccountNumber = transferRequest.fromAccountNumber,
                toAccountNumber = transferRequest.toAccountNumber,
                balance = transferRequest.balance,
                requestedAt = transferRequest.requestedAt
            )
        )
        
        // 실제 송금 수행
        try {
            accountService.transfer(
                TransferCommand(
                    fromAccountNumber = transferRequest.fromAccountNumber,
                    toAccountNumber = transferRequest.toAccountNumber,
                    balance = transferRequest.balance
                )
            )
            
            // 송금 성공 처리
            transferRecordService.completeTransfer(transferRequest.transferId)
            return ResponseEntity.ok(true)
        } catch (e: Exception) {
            // 송금 실패 처리
            transferRecordService.failTransfer(transferRequest.transferId)
            return ResponseEntity.internalServerError().body(e.message)
        }
    }
    
    /**
     * 송금 상태 조회
     */
    @GetMapping("/transfer/{transferId}/status")
    fun getTransferStatus(@PathVariable transferId: String): ResponseEntity<TransferStatusResponse> {
        val transferRecord = transferRecordService.findByTransferId(transferId)
        
        return if (transferRecord == null) {
            ResponseEntity.ok(
                TransferStatusResponse(
                    transferId = transferId,
                    status = TransferStatusResponse.STATUS_NOT_FOUND,
                    message = "Transfer not found"
                )
            )
        } else {
            ResponseEntity.ok(
                TransferStatusResponse(
                    transferId = transferId,
                    status = when (transferRecord.status) {
                        com.bifos.corebanking.entity.TransferStatus.COMPLETED -> TransferStatusResponse.STATUS_SUCCESS
                        com.bifos.corebanking.entity.TransferStatus.FAILED -> TransferStatusResponse.STATUS_FAIL
                        else -> "PROCESSING"
                    }
                )
            )
        }
    }
}