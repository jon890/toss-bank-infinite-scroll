package com.bifos.transfer.client

import com.bifos.transfer.client.dto.TransferExternalRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "core-banking", fallback = CoreBankingFeignFallback::class)
interface CoreBankingFeign {

    @PostMapping("/accounts/transfer")
    fun transfer(@RequestBody request: TransferExternalRequest): ResponseEntity<*>
}
