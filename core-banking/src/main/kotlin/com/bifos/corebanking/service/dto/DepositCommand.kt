package com.bifos.corebanking.service.dto

data class DepositCommand(
    val accountNumber: String,
    val balance: Long
) {
}