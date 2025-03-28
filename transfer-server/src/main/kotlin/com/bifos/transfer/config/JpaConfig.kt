package com.bifos.corebanking.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.bifos.transfer.repository"])
@EnableJpaAuditing
@EntityScan(basePackages = ["com.bifos.transfer.entity"])
class JpaConfig {
}