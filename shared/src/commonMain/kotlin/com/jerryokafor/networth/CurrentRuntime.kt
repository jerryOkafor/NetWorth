package com.jerryokafor.networth

expect fun determineCurrentRuntime(): CurrentRuntime

class CurrentRuntime(val name: String, rawVersion: String?) {
    companion object {
        val versionRegex = Regex("^[0-9]+(\\.[0-9]+)?")
    }

    val version = parseVersion(rawVersion)

    override fun toString() = "$name version $version"

    private fun parseVersion(rawVersion: String?): String {
        val result = rawVersion?.let { versionRegex.find(it) }
        return result?.value ?: "unknown"
    }
}
