package com.bifos.transfer.web

import com.bifos.transfer.service.TransferService
import com.bifos.transfer.service.dto.TransferCommand
import com.bifos.transfer.web.dto.TransferRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountController(
    private val transferService: TransferService
) {

    @PutMapping("/transfer")
    fun transfer(@RequestBody transferRequest: TransferRequest): ResponseEntity<*> {
        transferService.transfer(
            TransferCommand(
                transferRequest.fromAccountNumber,
                transferRequest.toAccountNumber,
                transferRequest.balance
            )
        )

        return ResponseEntity.ok(true)
    }
}