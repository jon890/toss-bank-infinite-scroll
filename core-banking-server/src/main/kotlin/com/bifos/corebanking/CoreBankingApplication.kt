package com.bifos.corebanking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class CoreBankingApplication {
}

fun main(args: Array<String>) {
    runApplication<CoreBankingApplication>(*args)
}