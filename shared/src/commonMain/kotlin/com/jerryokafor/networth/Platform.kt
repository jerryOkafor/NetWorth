package com.jerryokafor.networth

interface Platform {
    val name: String
}

expect class DefaultPlatform() : Platform
