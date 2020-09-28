plugins {
    id("org.jetbrains.kotlin.js") version "1.4.0"
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
    implementation( "org.jetbrains:kotlin-extensions:1.0.1-pre.112-kotlin-1.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC")
    implementation("dev.fritz2:core:0.8-SNAPSHOT")
    implementation("org.patternfly:patternfly-fritz2:0.1-SNAPSHOT")
    implementation(npm("@patternfly/patternfly", "4.42.2"))
    implementation(npm("clipboard", "2.0.6"))
    implementation(npm("highlight.js", "10.1.1"))
    implementation(devNpm("file-loader", "6.0.0"))
}

kotlin {
    js {
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