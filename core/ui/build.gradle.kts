import org.jetbrains.compose.compose

plugins {
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.compose)
    alias(libs.plugins.org.jetbrains.kotlinx.kover)
    alias(libs.plugins.io.kotest.multiplatform)
}

compose {
    kotlinCompilerPlugin.set(libs.versions.composeMultiplatformCompiler.get().toString())
}

compose {
    kotlinCompilerPlugin.set(libs.versions.composeMultiplatformCompiler.get().toString())
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ui"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // Chart
                implementation(libs.chart)
                implementation(libs.koalaplot.core)
            }
        }

        @Suppress("UnusedPrivateMember")
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(libs.kotlinx.coroutines.test)

                // kotest
                implementation(libs.kotest.framework.engine)
                implementation(libs.kotest.assertions.core)
                implementation(libs.kotest.property)

                // Compose
//                implementation(compose("org.jetbrains.compose.ui:ui-test-junit4"))
            }
        }

        @Suppress("UnusedPrivateMember")
        val androidMain by getting {
            dependencies {
                implementation(compose.preview)

                api(libs.activity.compose)
                api(libs.appcompat)
                api(libs.core.ktx)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting

        @Suppress("UnusedPrivateMember")
        val iosSimulatorArm64Main by getting

        @Suppress("UnusedPrivateMember")
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.jerryokafor.networth.core.ui"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeMultiplatformCompiler.get()
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
    implementation(libs.foundation.layout.android)
    implementation(libs.ui.tooling.preview.android)
    implementation(libs.material3)
}

koverReport {
    filters {}

    verify {}

    defaults {
        mergeWith("debug")

        xml { }
        html { }
        verify { }
        log { }
    }
}
