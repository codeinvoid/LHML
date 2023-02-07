package page

import AppTheme
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
import reader.config

@Composable
fun ThemePage(rootComponentContext: DefaultComponentContext, onExitClick: (Boolean) -> Unit ) {
    var isFollowSystem by remember { mutableStateOf(config.colorMode) }
    var isLightMode by remember { mutableStateOf(config.lightMode) }

    AppTheme(isFollowSystem = isFollowSystem, isLightMode = isLightMode, content = {
        Surface(
            modifier = Modifier.fillMaxSize().padding(8.dp).shadow(3.dp, RoundedCornerShape(20.dp), true),
            shape = RoundedCornerShape(20.dp)
        ) {
            MaterialTheme {
                CompositionLocalProvider(LocalScrollbarStyle provides defaultScrollbarStyle()) {
                    ProvideComponentContext(rootComponentContext) {
                        MainPage().index({onExitClick(it)}, {})
//                        MainContent(isFollowSystem = {
//                            isFollowSystem = it
//                        }, isLightMode = {
//                            isLightMode = it
//                        })
                    }
                }
            }
        }
    })
}
