package com.bifos.transfer.web.dto

import java.time.LocalDateTime

data class FindAccountHistoryRequest(
    val accountNumber: String,
    val baseTime: LocalDateTime,
    val count: Int
) {
}