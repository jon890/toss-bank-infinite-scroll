package com.bifos.corebanking.web

import com.bifos.corebanking.service.AccountService
import com.bifos.corebanking.service.dto.CreateAccountCommand
import com.bifos.corebanking.service.dto.DepositCommand
import com.bifos.corebanking.service.dto.TransferCommand
import com.bifos.corebanking.web.dto.CreateAccountRequest
import com.bifos.corebanking.web.dto.DepositRequest
import com.bifos.corebanking.web.dto.TransferRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountController(private val accountService: AccountService) {

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
     * 이체, 타행이체까지는 고려하지 않는다.
     */
    @PutMapping("/transfer")
    fun transfer(@RequestBody transferRequest: TransferRequest): ResponseEntity<*> {
        accountService.transfer(TransferCommand(
            fromAccountNumber = transferRequest.fromAccountNumber,
            toAccountNumber = transferRequest.toAccountNumber,
            balance = transferRequest.balance
        ))
        return ResponseEntity.ok(true)
    }
}