plugins {
    kotlin("js") version "1.4.20"
    kotlin("plugin.serialization") version "1.4.10"
}

group = "org.patternfly"
version = "0.1.0"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://oss.jfrog.org/artifactory/jfrog-dependencies")
    jcenter()
}

dependencies {
    implementation("org.jetbrains:kotlin-extensions:1.0.1-pre.129-kotlin-1.4.20")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("dev.fritz2:core:0.8")
    implementation("dev.fritz2:mvp:0.1.0")
    implementation("org.patternfly:patternfly-fritz2:0.1.0")
    implementation(npm("@github/time-elements", "3.1.1"))
    implementation(npm("@patternfly/patternfly", "4"))
    implementation(npm("clipboard", "2.0.6"))
    implementation(npm("highlight.js", "10.4.1"))
    implementation(devNpm("file-loader", "6.2.0"))
}

kotlin {
    js {
        sourceSets {
            named("main") {
                languageSettings.apply {
                    useExperimentalAnnotation("kotlin.ExperimentalStdlibApi")
                    useExperimentalAnnotation("kotlin.time.ExperimentalTime")
                    useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
                }
            }
        }
        compilations.named("main") {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
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
