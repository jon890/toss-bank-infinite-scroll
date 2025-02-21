package com.bifos.corebanking.repository

import com.bifos.corebanking.entity.Account

interface AccountRepository {
    fun createAccount(username: String): Account
}