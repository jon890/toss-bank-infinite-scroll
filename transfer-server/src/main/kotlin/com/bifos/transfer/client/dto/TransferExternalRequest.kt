package com.bifos.transfer.client.dto

import java.time.LocalDateTime

data class TransferExternalRequest(
    val transferId: String,
    val fromAccountNumber: String,
    val toAccountNumber: String,
    val balance: Long,
    val requestedAt: LocalDateTime
) {
}