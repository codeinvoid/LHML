import androidx.compose.animation.Crossfade
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.defaultScrollbarStyle
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowScope
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.*
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import kotlinx.coroutines.*
import java.awt.Dimension
import java.util.*
import kotlin.math.roundToInt


@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    MaterialTheme {
        Column {
            Text("Alfred Sisley", Modifier.padding(start = 32.dp))
            Text("3 minutes ago")
            Button(onClick = {
                text = "Hello, Desktop!"
            }) {
                Text(text)
            }
        }
    }
}

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
                if(windowState.size.width < 400.dp)
                    windowState.size = DpSize(400.dp,windowState.size.height)
            }
            Text(windowState.size.toString(),Modifier.offset(x = -10.dp, y = -5.dp))
            window.maximumSize = Dimension(windowSize.width.value.roundToInt(), windowSize.height.value.roundToInt())
            window.minimumSize = Dimension(windowSize.width.value.roundToInt(), windowSize.height.value.roundToInt())
            Surface(
                modifier = Modifier.fillMaxSize().padding(8.dp).shadow(3.dp, RoundedCornerShape(20.dp),true),
                shape = RoundedCornerShape(20.dp)
            ) {
                AppWindowTitleBar(onExitClick = { if (it) { exitApplication() } })
                MaterialTheme {
                    CompositionLocalProvider(LocalScrollbarStyle provides defaultScrollbarStyle()) {
                        ProvideComponentContext(rootComponentContext) {
                            MainContent()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WindowScope.AppWindowTitleBar(onExitClick: (Boolean) -> Unit) = WindowDraggableArea {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Box(Modifier.fillMaxWidth().height(48.dp)) {
        Row {
            IconButton(onClick = { onExitClick(true) },
                modifier = Modifier.padding(start = 12.dp, top = 10.dp).width(32.dp).height(32.dp),
                interactionSource = interactionSource) {
                var color = remember { Color.LightGray }
                Crossfade(targetState = isHovered) { screen ->
                    when (screen) {
                        true -> Icon(Icons.Rounded.Close, "Close", tint = Color.Red)
                        false -> Icon(Icons.Rounded.Close, "Close", tint = color)
                    }
                }

            }
        }
    }
}
