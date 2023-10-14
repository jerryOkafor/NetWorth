package com.jerryokafor.networth

import junit.framework.TestCase.assertEquals
import org.junit.Test

class PlatformTest {
    private val sut: Platform = DefaultPlatform()

    @Test
    fun platform_returnsCorrectName() {
        val expected = sut.name
        val actual = "Android ${android.os.Build.VERSION.SDK_INT}"
        assertEquals(expected, actual)
    }
}