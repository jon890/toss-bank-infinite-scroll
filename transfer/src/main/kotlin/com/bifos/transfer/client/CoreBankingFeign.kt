package com.bifos.transfer.client

import com.bifos.transfer.client.dto.TransferExternalRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient("core-banking")
interface CoreBankingFeign {

    @PutMapping("/accounts/transfer")
    fun transfer(@RequestBody transferRequest: TransferExternalRequest): ResponseEntity<*>
}