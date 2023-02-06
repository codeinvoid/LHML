package page

import AppTheme
import MainContent
import ProvideComponentContext
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.defaultScrollbarStyle
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.DefaultComponentContext

@Composable
fun ThemePage(rootComponentContext: DefaultComponentContext, onExitClick: (Boolean) -> Unit) {
    var isFollowSystem by remember { mutableStateOf(false) }
    var isLightMode by remember { mutableStateOf(true) }
    AppTheme(isFollowSystem = isFollowSystem, isLightMode = isLightMode, content = {
        Surface(
            modifier = Modifier.fillMaxSize().padding(8.dp).shadow(3.dp, RoundedCornerShape(20.dp), true),
            shape = RoundedCornerShape(20.dp)
        ) {
            MaterialTheme {
                AppWindowTitleBar(onExitClick = { onExitClick(it) })
                CompositionLocalProvider(LocalScrollbarStyle provides defaultScrollbarStyle()) {
                    ProvideComponentContext(rootComponentContext) {
                        MainContent(isFollowSystem = {
                            isFollowSystem = it
                        }, isLightMode = {
                            isLightMode = it
                        })
                    }
                }
            }
        }
    })
}

@Composable
fun AppWindowTitleBar(onExitClick: (Boolean) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Box(Modifier.fillMaxWidth().height(48.dp)) {
        Row {
            IconButton(onClick = { onExitClick(true) },
                modifier = Modifier.padding(start = 12.dp, top = 10.dp).width(32.dp).height(32.dp),
                interactionSource = interactionSource) {
                val color = MaterialTheme.colorScheme.error
                Crossfade(targetState = isHovered) { screen ->
                    when (screen) {
                        true -> Icon(
                            Icons.Rounded.Close, "Close",
                            tint = MaterialTheme.colorScheme.errorContainer)
                        false -> Icon(Icons.Rounded.Close, "Close", tint = color)
                    }
                }
            }
        }
    }
}