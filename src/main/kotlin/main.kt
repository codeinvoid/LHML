import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.*
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import kotlinx.coroutines.*
import page.ThemePage
import java.awt.Dimension
import java.util.*
import kotlin.math.roundToInt

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)
    application {
        val windowSize = DpSize(800.dp, 500.dp)
        val windowState = rememberWindowState(size = windowSize)
        LifecycleController(lifecycle, windowState)
        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Compose Navigator Example",
            undecorated = true,
            transparent = true,
        ) {
            LaunchedEffect(windowState.size) {
            if (windowState.size.width < 400.dp)
                windowState.size = DpSize(400.dp, windowState.size.height)
        }
            window.maximumSize =
                Dimension(windowSize.width.value.roundToInt(), windowSize.height.value.roundToInt())
            window.minimumSize =
                Dimension(windowSize.width.value.roundToInt(), windowSize.height.value.roundToInt())
           ThemePage(rootComponentContext, onExitClick={
               if (it) exitApplication()
           })
        }
    }
}
