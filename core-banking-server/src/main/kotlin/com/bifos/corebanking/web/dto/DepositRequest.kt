package com.bifos.corebanking.web.dto

data class DepositRequest(
    val accountNumber: String,
    val balance: Long
) {
}