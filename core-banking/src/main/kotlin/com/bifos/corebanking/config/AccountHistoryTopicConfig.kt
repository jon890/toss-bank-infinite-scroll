package com.bifos.corebanking.config

import com.bifos.constant.TopicNames
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class AccountHistoryTopicConfig {

    @Bean
    fun topic(): NewTopic {
        return TopicBuilder.name(TopicNames.ACCOUNT_HISTORY)
            .replicas(1)
            .partitions(1)
            .build()
    }
}