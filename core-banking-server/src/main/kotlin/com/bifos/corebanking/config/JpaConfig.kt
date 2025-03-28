package com.bifos.corebanking.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.bifos.corebanking.repository"])
@EnableJpaAuditing
@EntityScan(basePackages = ["com.bifos.corebanking.entity"])
class JpaConfig {
}