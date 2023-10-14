package com.jerryokafor.networth

import platform.UIKit.UIDevice


actual class DefaultPlatform actual constructor() : Platform {
    override val name: String
        get() = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}
