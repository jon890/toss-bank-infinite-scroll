package com.bifos.corebanking.service.dto

data class TransferCommand(
    val fromAccountNumber: String,
    val toAccountNumber: String,
    val balance: Long
)
