package com.jerryokafor.networth

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
