package com.jerryokafor.networth

import MainView
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val isDarkTheme = remember { mutableStateOf(true) }
            MainView { isDark ->
                isDarkTheme.value = isDark
                WindowInsetsControllerCompat(window, window.decorView)
                    .isAppearanceLightStatusBars = !isDark
            }

            DisposableEffect(isDarkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        lightScrim = Color.TRANSPARENT,
                        darkScrim = Color.TRANSPARENT,
                    ) { resources -> isDarkTheme.value },

                    navigationBarStyle = SystemBarStyle.auto(
                        lightScrim = lightScrim,
                        darkScrim = darkScrim,
                    ) { resources -> isDarkTheme.value },
                )
                onDispose {}
            }
        }
    }
}

@Suppress("MagicNumber")
private val lightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

@Suppress("MagicNumber")
private val darkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
