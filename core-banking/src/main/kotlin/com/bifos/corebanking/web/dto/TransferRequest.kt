package com.bifos.corebanking.web.dto

data class TransferRequest(
    val fromAccountNumber: String,
    val toAccountNumber: String,
    val balance: Long
) {
}