package com.bifos.corebanking.service.dto

data class DepositResult(
    val accountNumber: String,
    val prevBalance: Long,
    val currentBalance: Long,
    val deltaBalance: Long
) {
}