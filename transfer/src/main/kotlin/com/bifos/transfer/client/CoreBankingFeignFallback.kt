package com.bifos.transfer.client

import com.bifos.transfer.client.dto.TransferExternalRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class CoreBankingFeignFallback : CoreBankingFeign {
    override fun transfer(request: TransferExternalRequest): ResponseEntity<*> {
        // 폴백 로직 구현
        return ResponseEntity.internalServerError().body("Service temporarily unavailable")
    }
}
