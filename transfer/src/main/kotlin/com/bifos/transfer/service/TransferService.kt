package com.bifos.transfer.service

import com.bifos.transfer.client.CoreBankingFeign
import com.bifos.transfer.client.dto.TransferExternalRequest
import com.bifos.transfer.service.dto.TransferCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransferService(
    private val coreBankingFeign: CoreBankingFeign
) {

    @Transactional
    fun transfer(transferCommand: TransferCommand) {
        coreBankingFeign.transfer(
            TransferExternalRequest(
                fromAccountNumber = transferCommand.fromAccountNumber,
                toAccountNumber = transferCommand.toAccountNumber,
                balance = transferCommand.balance
            )
        )
    }
}