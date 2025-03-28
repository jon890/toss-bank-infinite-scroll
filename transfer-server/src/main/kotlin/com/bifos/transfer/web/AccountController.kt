package com.bifos.transfer.web

import com.bifos.transfer.service.TransferService
import com.bifos.transfer.service.dto.TransferCommand
import com.bifos.transfer.web.dto.TransferRequest
import com.bifos.transfer.web.dto.TransferResponse
import com.bifos.transfer.web.dto.TransferStatusResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val transferService: TransferService
) {

    @PutMapping("/transfer")
    fun transfer(@RequestBody transferRequest: TransferRequest): ResponseEntity<TransferResponse> {
        val transferId = transferService.transfer(
            TransferCommand(
                transferRequest.fromAccountNumber,
                transferRequest.toAccountNumber,
                transferRequest.balance
            )
        )

        return ResponseEntity.ok(TransferResponse(transferId = transferId))
    }
    
    @GetMapping("/transfer/{transferId}/status")
    fun getTransferStatus(@PathVariable transferId: String): ResponseEntity<TransferStatusResponse> {
        val status = transferService.checkTransferStatus(transferId)
        return ResponseEntity.ok(TransferStatusResponse(
            transferId = transferId,
            status = status.name
        ))
    }
}