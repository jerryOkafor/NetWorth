plugins {
    kotlin("multiplatform")
    id("com.android.library")
    alias(libs.plugins.org.jetbrains.kotlinx.kover)
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // put your multiplatform dependencies here
            }
        }

        @Suppress("UnusedPrivateMember")
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        @Suppress("UnusedPrivateMember")
        val androidMain by getting {
            dependencies {}
        }

        @Suppress("UnusedPrivateMember")
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        @Suppress("UnusedPrivateMember")
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting

        @Suppress("UnusedPrivateMember")
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
            dependencies {}
        }
    }
}

android {
    namespace = "com.jerryokafor.networth.common"
    compileSdk = 34

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
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
