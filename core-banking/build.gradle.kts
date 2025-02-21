plugins {
    libs.plugins.kotlin.spring.get().pluginId
    libs.plugins.kotlin.jpa.get().pluginId
}

flyway {
    url = "jdbc:mysql://localhost:13306/corebanking"
    user = "user"
    password = "password"
}

allOpen {
    annotation("jakarta.persistence.Entity")
}

dependencies {
    implementation(libs.spring.boot.web)
    implementation(libs.spring.kafka)
    implementation(libs.spring.data.jpa)
    implementation(libs.mysql.connector.j)
}