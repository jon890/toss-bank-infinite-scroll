package com.bifos.corebanking.service

import com.bifos.constant.TopicNames
import com.bifos.corebanking.service.dto.AccountHistoryProduceCommand
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class AccountHistoryProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    fun produce(produceCommand: AccountHistoryProduceCommand) {
        kafkaTemplate.send(TopicNames.ACCOUNT_HISTORY, objectMapper.writeValueAsString(produceCommand))
    }
}