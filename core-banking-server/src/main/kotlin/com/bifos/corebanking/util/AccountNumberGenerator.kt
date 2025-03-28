package com.bifos.corebanking.util

import java.util.concurrent.ThreadLocalRandom
import java.util.stream.Collectors
import java.util.stream.IntStream


object AccountNumberGenerator {

    /**
     * 1234-1234-1234 형식의 계좌번호를 만든다
     */
    fun generate(): String {
        val random = ThreadLocalRandom.current()
        return IntStream.range(0, 3)
            .mapToObj {
                random.nextInt(9999).toString()
            }.collect(Collectors.joining("-"))
    }
}