package com.bifos.transfer.web

import com.bifos.transfer.service.AccountHistoryService
import com.bifos.transfer.service.dto.FindAccountHistoryCommand
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account-histories")
class AccountHistoryController(private val accountHistoryService: AccountHistoryService) {

    /**
     * 계좌번호로 이체내역을 조회한다.
     */
    @GetMapping("/account-number")
    fun getHistoryByAccountNumber(@RequestBody findAccountHistoryCommand: FindAccountHistoryCommand): ResponseEntity<*> {
        val histories = accountHistoryService.getHistories(findAccountHistoryCommand)
        return ResponseEntity.ok(histories)
    }
}