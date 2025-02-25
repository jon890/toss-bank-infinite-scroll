package com.bifos.corebanking.service.dto

import com.bifos.corebanking.entity.Account

data class CreateAccountHistoryCommand(
    val accountNumber: String,
    val prevBalance: Long,
    val currentBalance: Long,
    val deltaBalance: Long
) {
}