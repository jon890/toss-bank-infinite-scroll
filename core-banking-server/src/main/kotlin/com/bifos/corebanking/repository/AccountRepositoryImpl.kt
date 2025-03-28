package com.bifos.corebanking.repository

import com.bifos.corebanking.entity.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl @Autowired constructor(private val accountJpaRepository: AccountJpaRepository) :
    AccountRepository {

    override fun createAccount(username: String, accountNumber: String): Account {
        val account = Account(accountNumber, username, 0)
        return accountJpaRepository.save(account)
    }

    override fun findByAccountNumber(accountNumber: String): Account? {
        return accountJpaRepository.findByAccountNumber(accountNumber)
    }

    override fun findByAccountNumberForUpdate(accountNumber: String): Account? {
        return accountJpaRepository.findByAccountNumberForUpdate(accountNumber)
    }
}