package com.bifos.transfer.service.dto

import java.time.LocalDateTime

data class FindAccountHistoryCommand(
    val accountNumber: String,
    val baseTime: LocalDateTime,
    val count: Int
) {
}