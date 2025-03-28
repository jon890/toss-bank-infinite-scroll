import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.jpa)
    alias(libs.plugins.flyway)
}

allprojects {
    group = "com.bifos"
    version = "1.0"

    repositories {
        mavenCentral()
    }
}

// todo 위와 같이 안할 방법은 없나..?
val kotlinJvmPluginId = libs.plugins.kotlin.jvm.get().pluginId
val kotlinSpringPluginId = libs.plugins.kotlin.spring.get().pluginId
val kotlinJpaPluginId = libs.plugins.kotlin.jpa.get().pluginId
val springDependencyManagementPluginId = libs.plugins.spring.dependency.management.get().pluginId
val springBootPluginId = libs.plugins.spring.boot.get().pluginId
val flywayPluginId = libs.plugins.flyway.get().pluginId

val kotlinReflectLib = libs.kotlin.reflect.get()

subprojects {
    apply(plugin = kotlinJvmPluginId)
    apply(plugin = kotlinSpringPluginId)
    apply(plugin = kotlinJpaPluginId)
    apply(plugin = springDependencyManagementPluginId)
    apply(plugin = springBootPluginId)
    apply(plugin = flywayPluginId)

    dependencies {
        implementation(kotlinReflectLib)
    }

    kotlin {
       jvmToolchain(21)
    }

    tasks {
        withType<JavaCompile> {
            sourceCompatibility = "21"
            targetCompatibility = "21"
        }

        withType<KotlinCompile> {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_21)
                freeCompilerArgs.add("-Xjsr305=strict")
            }
        }

        withType<Test> {
            useJUnitPlatform()
        }
    }
}
