package com.bifos.transfer.client.dto

data class TransferExternalRequest(
    val fromAccountNumber: String,
    val toAccountNumber: String,
    val balance: Long
) {
}