import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// ------------------------------------------------------ core

plugins {
    kotlin("js") version Versions.kotlin
    kotlin("plugin.serialization") version Versions.serialization
}

group = "org.patternfly"
version = "0.1.0"

// ------------------------------------------------------ repositories

val repositories = arrayOf(
    "https://oss.sonatype.org/content/repositories/snapshots/",
    "https://s01.oss.sonatype.org/content/repositories/snapshots/"
)

repositories {
    mavenLocal()
    mavenCentral()
    repositories.forEach { maven(it) }
}

// ------------------------------------------------------ dependencies

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serializationJson}")
    implementation("dev.fritz2:core:${Versions.fritz2}")
    implementation("dev.fritz2:mvp:${Versions.mvp}")
    implementation("org.patternfly:patternfly-fritz2:${Versions.patternflyFritz2}")
    implementation(npm("@github/time-elements", Versions.timeElements))
    implementation(npm("@patternfly/patternfly", Versions.patternfly))
    implementation(npm("clipboard", Versions.clipboard))
    implementation(npm("highlight.js", Versions.highlight))
    implementation(devNpm("file-loader", Versions.fileLoader))
}

// ------------------------------------------------------ kotlin/js

kotlin {
    js(IR) {
        sourceSets {
            named("main") {
                languageSettings.apply {
                    optIn("kotlin.ExperimentalStdlibApi")
                    optIn("kotlin.time.ExperimentalTime")
                    optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                }
            }
        }
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
        binaries.executable()
    }
}
