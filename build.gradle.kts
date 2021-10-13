@file:Suppress("UnstableApiUsage")

// https://youtrack.jetbrains.com/issue/KTIJ-19369#focus=Comments-27-5181027.0-0
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.js)
    alias(libs.plugins.serialization)
}

group = "org.patternfly"
version = "0.1.0"

val repositories = arrayOf(
    "https://oss.sonatype.org/content/repositories/snapshots/",
    "https://s01.oss.sonatype.org/content/repositories/snapshots/"
)

repositories {
    mavenLocal()
    mavenCentral()
    repositories.forEach { maven(it) }
}

dependencies {
    implementation(libs.bundles.fritz2)
    implementation(libs.serialization.json)
    implementation(npm("@github/time-elements", libs.versions.timeElements.get()))
    implementation(npm("@patternfly/patternfly", libs.versions.patternFly.get()))
    implementation(npm("clipboard", libs.versions.clipboard.get()))
    implementation(npm("highlight.js", libs.versions.highlight.get()))
    implementation(devNpm("file-loader", libs.versions.fileLoader.get()))
}

kotlin {
    js(IR) {
        sourceSets {
            named("main") {
                languageSettings.apply {
                    optIn("kotlin.ExperimentalStdlibApi")
                    optIn("kotlin.time.ExperimentalTime")
                    optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                    optIn("kotlinx.coroutines.FlowPreview")
                    optIn("kotlinx.serialization.ExperimentalSerializationApi")
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

// workaround for https://youtrack.jetbrains.com/issue/KT-49124
// see also https://github.com/webpack/webpack-cli/issues/2990
rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
    versions.webpackCli.version = "4.9.0"
}
