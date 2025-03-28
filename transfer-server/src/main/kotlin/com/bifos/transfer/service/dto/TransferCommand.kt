package com.bifos.transfer.service.dto

data class TransferCommand(
    val fromAccountNumber: String,
    val toAccountNumber: String,
    val balance: Long
) {
}