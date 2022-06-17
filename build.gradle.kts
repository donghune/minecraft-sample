buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("gradle.plugin.com.github.johnrengelman:shadow:7.1.2")
    }
}

val kotlinVersion = "1.6.10"

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.dokka") version "1.6.21"
    id("maven-publish")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.github.johnrengelman.shadow")

    tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        archiveFileName.set("${project.name}-${project.version}.jar")
    }

    repositories {
        mavenCentral()
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://oss.sonatype.org/content/repositories/central")
        maven("https://maven.enginehub.org/repo/")
        maven("https://jitpack.io")
        maven {
            url = uri("https://maven.pkg.github.com/donghune/mokoko")
            credentials {
                username = System.getenv()["GITHUB_ACTOR"]
                password = System.getenv()["GITHUB_TOKEN"]
            }
        }
    }

    dependencies {
        testImplementation(kotlin("test"))
        compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
        implementation("io.github.donghune:mokoko:1.0.4-1.18.2")

        compileOnly("io.github.monun:kommand-api:2.11.0")
        compileOnly("io.github.monun:heartbeat-coroutines:0.0.3")

        compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
        compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
        compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.3")
        compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/donghune/minecraft-${project.name}")
            credentials {
                username = System.getenv()["GITHUB_ACTOR"]
                password = System.getenv()["GITHUB_TOKEN"]
            }
        }
    }
    publications {
        register<MavenPublication>("jar") {
            from(components["java"])
        }
    }
}