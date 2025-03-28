plugins {
    libs.plugins.kotlin.spring.get().pluginId
}

dependencies {
    implementation(libs.spring.boot.webflux)
    implementation(platform(libs.spring.cloud))
    implementation(libs.spring.cloud.eureka.server)
} 