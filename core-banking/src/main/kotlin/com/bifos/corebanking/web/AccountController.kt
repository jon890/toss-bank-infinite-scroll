package com.bifos.corebanking.web

import com.bifos.corebanking.service.AccountService
import com.bifos.corebanking.service.dto.CreateAccountCommand
import com.bifos.corebanking.service.dto.DepositCommand
import com.bifos.corebanking.web.dto.CreateAccountRequest
import com.bifos.corebanking.web.dto.DepositRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountController(private val accountService: AccountService) {

    @PostMapping("")
    fun createAccount(@RequestBody createAccountRequest: CreateAccountRequest): ResponseEntity<*> {
        val createAccount =
            accountService.createAccount(CreateAccountCommand(createAccountRequest.username))
        return ResponseEntity.ok(createAccount)
    }

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
}