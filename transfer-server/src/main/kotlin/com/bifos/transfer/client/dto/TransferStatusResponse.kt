package com.bifos.transfer.client.dto

data class TransferStatusResponse(
    val transferId: String,
    val status: String,
    val message: String? = null
) {
    companion object {
        const val STATUS_SUCCESS = "SUCCESS"
        const val STATUS_FAIL = "FAIL"
        const val STATUS_NOT_FOUND = "NOT_FOUND"
    }
} 