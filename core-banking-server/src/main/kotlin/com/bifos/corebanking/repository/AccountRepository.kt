package com.bifos.corebanking.repository

import com.bifos.corebanking.entity.Account

interface AccountRepository {
    fun createAccount(username: String, accountNumber: String): Account

    fun findByAccountNumber(accountNumber: String) : Account?

    fun findByAccountNumberForUpdate(accountNumber: String): Account?
}