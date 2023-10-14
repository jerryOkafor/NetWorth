package com.jerryokafor.networth

class Greeting {
    private val platform: Platform = DefaultPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
