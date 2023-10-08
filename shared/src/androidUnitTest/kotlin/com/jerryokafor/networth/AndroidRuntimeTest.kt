package com.jerryokafor.networth

import junit.framework.TestCase.assertEquals
import org.junit.Test
import kotlin.test.assertContains

class AndroidRuntimeTest {
    @Test
    fun shouldDetectAndroid() {
        val runtime = determineCurrentRuntime()
//        assertContains(runtime.name, "OpenJDK")
        assertContains(runtime.name, "Android")
//        assertEquals(runtime.version, "17.0")
        assertEquals(runtime.version, "17.0")
    }
}
