package com.bifos.transfer.web.dto

data class TransferRequest(
    val fromAccountNumber: String,
    val toAccountNumber: String,
    val balance: Long
) {
}