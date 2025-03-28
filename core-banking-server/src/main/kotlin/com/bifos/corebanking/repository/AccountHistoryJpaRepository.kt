package com.bifos.corebanking.repository

import com.bifos.corebanking.entity.AccountHistory
import org.springframework.data.jpa.repository.JpaRepository

interface AccountHistoryJpaRepository : JpaRepository<AccountHistory, Long>{
}