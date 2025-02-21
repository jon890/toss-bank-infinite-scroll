package com.bifos.corebanking.repository

import com.bifos.corebanking.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountJpaRepository : JpaRepository<Account, UUID> {

}