package com.bifos.transfer.web

import com.bifos.transfer.service.AccountHistoryService
import com.bifos.transfer.service.dto.FindAccountHistoryCommand
import com.bifos.transfer.web.dto.FindAccountHistoryRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account-histories")
class AccountHistoryController(private val accountHistoryService: AccountHistoryService) {

    /**
     * 계좌번호로 이체내역을 조회한다.
     */
    @GetMapping("/account-number")
    fun getHistoryByAccountNumber(findAccountHistoryRequest: FindAccountHistoryRequest): ResponseEntity<*> {
        val histories = accountHistoryService.getHistories(
            FindAccountHistoryCommand(
                accountNumber = findAccountHistoryRequest.accountNumber,
                baseTime = findAccountHistoryRequest.baseTime,
                count = findAccountHistoryRequest.count
            )
        )
        return ResponseEntity.ok(histories)
    }
}