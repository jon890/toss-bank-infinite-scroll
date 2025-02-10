package com.bifos.corebanking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoreBankingApplication {
}

fun main(args: Array<String>) {
    runApplication<CoreBankingApplication>(*args)
}