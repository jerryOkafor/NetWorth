@file:Suppress("Filename")

import androidx.compose.runtime.Composable

@Composable
fun MainView(onAppThemeChange: (isDarkTheme: Boolean) -> Unit = {}) = App(onAppThemeChange)
