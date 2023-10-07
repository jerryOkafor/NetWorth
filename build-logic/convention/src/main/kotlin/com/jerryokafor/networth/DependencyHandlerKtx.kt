package com.jerryokafor.networth

import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.detekt(provider: Any) {
    add("detektPlugins", provider)
}
