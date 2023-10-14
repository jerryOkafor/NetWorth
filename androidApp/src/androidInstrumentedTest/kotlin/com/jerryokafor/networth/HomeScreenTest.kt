package com.jerryokafor.networth

import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    fun textViewRendersAppName() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val text = device.findObject(By.text(context.getString(R.string.app_name)))
            .text

        assertEquals(text, context.getString(R.string.app_name))
    }
}
