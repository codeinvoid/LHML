import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.AlertDialog
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.zIndex
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.*
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import config.Config
import config.Setting
import kotlinx.coroutines.*
import page.ThemePage
import java.awt.Dimension
import java.io.File
import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path
import kotlin.math.roundToInt

fun file() {
    if (!Files.isDirectory(Path("./config"))) {
        Files.createDirectory(Path("./config"))
    }

    val file = File("./config/setting.toml")
    if (!file.exists()){
        Config(file).write(Setting::class.java, Setting("0.0.1", colorMode = false, lightMode = false))
    }
}

@OptIn(ExperimentalDecomposeApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)
    file()
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
            window.maximumSize = Dimension(windowSize.width.value.roundToInt(), windowSize.height.value.roundToInt())
            window.minimumSize = Dimension(windowSize.width.value.roundToInt(), windowSize.height.value.roundToInt())
            ThemePage(rootComponentContext) {
                exitApplication()
            }
            AppWindowTitleBar()
        }
    }
}

@Composable
fun WindowScope.AppWindowTitleBar() = WindowDraggableArea {
    Box(Modifier.fillMaxWidth().height(48.dp).zIndex(0f)){
        Row(Modifier.fillMaxSize()) {
            Spacer(Modifier.fillMaxSize())
        }
    }
}
