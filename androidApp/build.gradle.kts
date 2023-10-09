plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
    alias(libs.plugins.org.jetbrains.kotlinx.kover)
}

kotlin {
    androidTarget()
    sourceSets {
        @Suppress("UnusedPrivateMember")
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.activity.ktx)
                implementation(project(":shared"))
                implementation(project(":sharedUI"))
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.jerryokafor.networth"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "com.myapplication.MyApplication"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionCode = 1
        versionName = "1.0"
    }

    lint {
        baseline = file("lint-baseline.xml")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}
dependencies {
    implementation(libs.androidx.foundation.android)
}

koverReport {
    filters {}

    verify {}

    androidReports("debug") {
        filters {}

        xml { }
        html { }
        verify { }
        log { }
    }
}
