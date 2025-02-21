package com.bifos.corebanking.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import java.util.UUID

@Table
@Entity
class Account(
    username: String,
    balance: Long
) {
    @Id
    val id: UUID = UUID.randomUUID()

    /**
     * 해당 값으로 통장을 구분하기 위해 사용
     * TODO 한 유저가 같은 은행의 여러 통장을 사용할 수 있지 않나..?
     */
    @Column(unique = true, nullable = false)
    var username: String = username

    @Column
    var balance: Long = balance

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.now()
        protected set
}