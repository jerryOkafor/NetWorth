plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
    alias(libs.plugins.io.gitlab.arturbosch.detekt).apply(false)
    alias(libs.plugins.org.jetbrains.kotlinx.kover).apply(false)

    id("com.jerryokafor.networth.detekt")
    id("com.jerryokafor.networth.ktlint")
}
