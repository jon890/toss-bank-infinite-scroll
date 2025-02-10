import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
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
val springDependencyManagementPluginId = libs.plugins.spring.dependency.management.get().pluginId
val springBootPluginId = libs.plugins.spring.boot.get().pluginId

subprojects {
    apply(plugin = kotlinJvmPluginId)
    apply(plugin = kotlinSpringPluginId)
    apply(plugin = springDependencyManagementPluginId)
    apply(plugin = springBootPluginId)

    dependencies {
        //
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
