@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

group = "org.patternfly"
version = "0.1.0"

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
    implementation(libs.bundles.pfk)
    implementation(libs.serialization.json)
    implementation(npm("@github/time-elements", libs.versions.timeElements.get()))
    implementation(npm("@patternfly/patternfly", libs.versions.patternFly.get()))
    implementation(npm("clipboard", libs.versions.clipboard.get()))
    implementation(npm("highlight.js", libs.versions.highlight.get()))
    implementation(devNpm("css-loader", libs.versions.cssLoader.get()))
    implementation(devNpm("sass", libs.versions.sass.get()))
    implementation(devNpm("sass-loader", libs.versions.sassLoader.get()))
    implementation(devNpm("style-loader", libs.versions.styleLoader.get()))
}

val webDir = file("src/main/web")

kotlin {
    js {
        browser {
            runTask {
                outputFileName = "main.bundle.js"
                sourceMaps = true
                devServer = KotlinWebpackConfig.DevServer(
                    open = false,
                    port = 3000,
                    static = mutableListOf("$buildDir/processedResources/js/main")
                )
            }
            webpackTask {
                outputFileName = "main.bundle.js"
            }
        }
        binaries.executable()
    }
    sourceSets["main"].resources.srcDir(webDir)
}
