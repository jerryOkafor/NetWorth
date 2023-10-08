package com.jerryokafor.networth

import kotlin.test.Test
import kotlin.test.assertEquals

class CurrentRuntimeTest {
    @Test
    fun shouldDisplayDetails() {
        val runtime = CurrentRuntime("MyRuntime", "1.1")
        assertEquals("MyRuntime version 1.1", runtime.toString())
    }

    @Test
    fun shouldHandleNullVersion() {
        val runtime = CurrentRuntime("MyRuntime", null)
        assertEquals("MyRuntime version unknown", runtime.toString())
    }

    @Test
    fun shouldParseNumberFromVersionString() {
        val runtime = CurrentRuntime("MyRuntime", "1.2 Alpha Experimental")
        assertEquals("MyRuntime version 1.2", runtime.toString())
    }

    @Test
    fun shouldHandleMissingVersion() {
        val runtime = CurrentRuntime("MyRuntime", "Alpha Experimental")
        assertEquals("MyRuntime version unknown", runtime.toString())
    }
}
