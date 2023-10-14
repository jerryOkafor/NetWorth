package com.jerryokafor.networth

actual class DefaultPlatform actual constructor() : Platform {
    override val name: String
        get() = "Android ${android.os.Build.VERSION.SDK_INT}"
}
