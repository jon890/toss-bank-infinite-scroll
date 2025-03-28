package com.bifos.corebanking.repository

import com.bifos.corebanking.entity.Account
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface AccountJpaRepository : JpaRepository<Account, Long> {

    fun findByAccountNumber(accountNumber: String): Account?

    @Query("select a from Account a where a.accountNumber = :accountNumber")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByAccountNumberForUpdate(accountNumber: String) : Account?

}