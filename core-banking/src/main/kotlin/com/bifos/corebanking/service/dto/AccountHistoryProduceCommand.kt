package com.bifos.corebanking.service.dto

import java.time.LocalDateTime

data class AccountHistoryProduceCommand(
    val accountNumber: String,
    val prevBalance: Long,
    val currentBalance: Long,
    val deltaBalance: Long,
    val createdAt: LocalDateTime,
) {
}