package com.bifos.corebanking.web.dto

import java.time.LocalDateTime

data class TransferRequest(
    val transferId: String,
    val fromAccountNumber: String,
    val toAccountNumber: String,
    val balance: Long,
    val requestedAt: LocalDateTime
) {
}