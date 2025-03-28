package com.bifos.transfer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class TransferApplication {
}

fun main(args: Array<String>) {
    runApplication<TransferApplication>(*args)
}