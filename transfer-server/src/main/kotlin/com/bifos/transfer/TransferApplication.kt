package com.bifos.transfer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
class TransferApplication {
}

fun main(args: Array<String>) {
    runApplication<TransferApplication>(*args)
}