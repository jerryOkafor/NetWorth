package com.jerryokafor.networth

actual fun determineCurrentRuntime(): CurrentRuntime {
    val name = "Android" // System.getProperty("java.vm.name") ?: "Android"

    val version = "17.0" // System.getProperty("java.version")

    return CurrentRuntime(name, version)
}
