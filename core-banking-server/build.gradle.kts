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
    
    // Spring Cloud 의존성 추가
    implementation(platform(libs.spring.cloud))
    implementation(libs.spring.cloud.eureka.client)
}