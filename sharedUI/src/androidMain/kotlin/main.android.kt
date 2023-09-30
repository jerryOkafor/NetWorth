import androidx.compose.runtime.Composable

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView(onAppThemeChange: (isDarkTheme: Boolean) -> Unit = {}) = App(onAppThemeChange)
