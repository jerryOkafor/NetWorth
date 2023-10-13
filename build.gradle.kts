plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.org.jetbrains.kotlin.jvm).apply(false)
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform).apply(false)
    alias(libs.plugins.org.jetbrains.kotlin.android).apply(false)
    alias(libs.plugins.com.android.application).apply(false)
    alias(libs.plugins.com.android.library).apply(false)
    alias(libs.plugins.org.jetbrains.compose).apply(false)
    alias(libs.plugins.io.gitlab.arturbosch.detekt).apply(false)
    alias(libs.plugins.org.jetbrains.kotlinx.kover).apply(false)

    id("com.jerryokafor.networth.detekt")
    id("com.jerryokafor.networth.ktlint")
}
