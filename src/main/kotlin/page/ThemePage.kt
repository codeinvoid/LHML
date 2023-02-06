package page

import AppTheme
import MainContent
import ProvideComponentContext
import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.defaultScrollbarStyle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.DefaultComponentContext

@Composable
fun ThemePage(rootComponentContext: DefaultComponentContext) {
    var isFollowSystem by remember { mutableStateOf(false) }
    var isLightMode by remember { mutableStateOf(true) }
    AppTheme(isFollowSystem = isFollowSystem, isLightMode = isLightMode, content = {
        Surface(
            modifier = Modifier.fillMaxSize().padding(8.dp).shadow(3.dp, RoundedCornerShape(20.dp), true),
            shape = RoundedCornerShape(20.dp)
        ) {
            MaterialTheme {
                CompositionLocalProvider(LocalScrollbarStyle provides defaultScrollbarStyle()) {
                    ProvideComponentContext(rootComponentContext) {
                        //AppWindowTitleBar(onExitClick)
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