package com.jerryokafor.networth

import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.Platform

@OptIn(ExperimentalNativeApi::class)
actual fun determineCurrentRuntime(): CurrentRuntime {
    val name = Platform.osFamily.name.lowercase()
    return CurrentRuntime(name, null)
}
