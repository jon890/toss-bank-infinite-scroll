package com.bifos.transfer.client

import com.bifos.transfer.client.dto.TransferExternalRequest
import com.bifos.transfer.client.dto.TransferStatusResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class CoreBankingFeignFallback : CoreBankingFeign {
    override fun transfer(request: TransferExternalRequest): ResponseEntity<*> {
        // 폴백 로직 구현
        return ResponseEntity.internalServerError().body("Service temporarily unavailable")
    }
    
    override fun getTransferStatus(transferId: String): ResponseEntity<TransferStatusResponse> {
        return ResponseEntity.internalServerError().body(
            TransferStatusResponse(
                transferId = transferId,
                status = "ERROR",
                message = "Service temporarily unavailable"
            )
        )
    }
}
