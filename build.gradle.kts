plugins {
    kotlin("js") version "1.4.20"
    kotlin("plugin.serialization") version "1.4.0"
}

group = "org.patternfly"
version = "0.1-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://oss.jfrog.org/artifactory/jfrog-dependencies")
    jcenter()
}

dependencies {
    implementation("org.jetbrains:kotlin-extensions:1.0.1-pre.112-kotlin-1.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0")
    implementation("dev.fritz2:core:0.8-SNAPSHOT")
    implementation("dev.fritz2:elemento:0.0.4")
    implementation("dev.fritz2:mvp:0.0.4")
    implementation("org.patternfly:patternfly-fritz2:0.0.4")
    implementation(npm("@github/time-elements", "3.1.1"))
    implementation(npm("@patternfly/patternfly", "4.59.1"))
    implementation(npm("clipboard", "2.0.6"))
    implementation(npm("highlight.js", "10.1.1"))
    implementation(devNpm("file-loader", "6.0.0"))
}

kotlin {
    js {
        compilations.named("main") {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
            }
        }
        browser {
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
        }
        binaries.executable()
    }
}
