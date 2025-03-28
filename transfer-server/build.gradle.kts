plugins {
    libs.plugins.kotlin.spring.get().pluginId
    libs.plugins.kotlin.jpa.get().pluginId
}

allOpen {
    annotation("jakarta.persistence.Entity")
}

dependencies {
    implementation(libs.spring.boot.web)
    implementation(libs.spring.kafka)
    implementation(libs.spring.data.jpa)
    implementation(libs.mysql.connector.j)
    implementation(libs.flyway.mysql)
    implementation(libs.flyway.core)
    
    // WebClient 의존성 추가
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    // Spring Cloud 의존성 추가
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2023.0.0"))
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
}