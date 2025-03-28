package com.bifos.transfer.client

import com.bifos.transfer.client.dto.TransferExternalRequest
import com.bifos.transfer.client.dto.TransferStatusResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "core-banking", fallback = CoreBankingFeignFallback::class)
interface CoreBankingFeign {

    @PostMapping("/accounts/transfer")
    fun transfer(@RequestBody request: TransferExternalRequest): ResponseEntity<*>
    
    @GetMapping("/accounts/transfer/{transferId}/status")
    fun getTransferStatus(@PathVariable transferId: String): ResponseEntity<TransferStatusResponse>
}
