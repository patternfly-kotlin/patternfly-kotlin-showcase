@file:Suppress("UnstableApiUsage")

// https://youtrack.jetbrains.com/issue/KTIJ-19369#focus=Comments-27-5181027.0-0
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.js)
    // Activate as soon as
    // https://github.com/google/ksp/issues/33 and
    // https://github.com/varabyte/kobweb/issues/4
    // have been fixed
    // alias(libs.plugins.ksp)
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
                    optIn("kotlin.RequiresOptIn")
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
