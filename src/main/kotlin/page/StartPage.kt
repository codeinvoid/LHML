package page

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import Screen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class StartPage {
    @Composable
    fun start(onItemClick: (Screen) -> Unit) {
        Box(
            contentAlignment = Alignment.Center, // you apply alignment to all children
            modifier = Modifier.fillMaxSize()
        ) {
            Column(Modifier.align(alignment = Alignment.Center)) {
                Text("LHML 启动器")
                Button(onClick = { onItemClick(Screen.OnlineAuth)},Modifier.align(alignment = Alignment.CenterHorizontally)) {
                    Text(text = "正版")
                }
                Button(onClick = { onItemClick(Screen.OfflineAuth)},Modifier.align(alignment = Alignment.CenterHorizontally)) {
                    Text(text = "离线")
                }
                Button(onClick = { onItemClick(Screen.ThirdParty)},Modifier.align(alignment = Alignment.CenterHorizontally)) {
                    Text(text = "其他")
                }
            }
        }
    }
}