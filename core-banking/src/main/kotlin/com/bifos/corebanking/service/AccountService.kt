package com.bifos.corebanking.service

import com.bifos.corebanking.entity.Account
import com.bifos.corebanking.repository.AccountRepository
import com.bifos.corebanking.service.dto.CreateAccountCommand
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {
    fun createAccount(command : CreateAccountCommand) : Account {
        return accountRepository.createAccount(command.username)
    }
}