plugins {
    kotlin("js") version "1.4.31"
    kotlin("plugin.serialization") version "1.4.30"
}

group = "org.patternfly"
version = "0.1.0"

object Versions {
    // dependencies
    const val fritz2 = "0.9.2"
    const val mvp = "0.3.0"
    const val patternflyFritz2 = "0.3.0-SNAPSHOT"
    const val serialization = "1.1.0"

    // NPM (dev) dependencies
    const val clipboard = "2.0.6"
    const val fileLoader = "6.2.0"
    const val highlight = "10.4.1"
    const val patternfly = "4"
    const val timeElements = "3.1.1"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}")
    implementation("dev.fritz2:core:${Versions.fritz2}")
    implementation("dev.fritz2:mvp:${Versions.mvp}")
    implementation("org.patternfly:patternfly-fritz2:${Versions.patternflyFritz2}")
    implementation(npm("@github/time-elements", Versions.timeElements))
    implementation(npm("@patternfly/patternfly", Versions.patternfly))
    implementation(npm("clipboard", Versions.clipboard))
    implementation(npm("highlight.js", Versions.highlight))
    implementation(devNpm("file-loader", Versions.fileLoader))
}

kotlin {
    js(IR) {
        sourceSets {
            named("main") {
                languageSettings.apply {
                    useExperimentalAnnotation("kotlin.ExperimentalStdlibApi")
                    useExperimentalAnnotation("kotlin.time.ExperimentalTime")
                    useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
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
