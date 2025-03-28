package com.bifos.corebanking.service.dto

import java.time.LocalDateTime

data class TransferRecordCommand(
    val transferId: String,
    val fromAccountNumber: String,
    val toAccountNumber: String,
    val balance: Long,
    val requestedAt: LocalDateTime
) 