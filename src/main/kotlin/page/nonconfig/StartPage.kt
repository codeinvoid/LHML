package page.nonconfig

import Screen
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.NextButton
import component.Progress

class StartPage {
    @Composable
    fun start(onItemClick: (Screen) -> Unit, progress: Float, onBack: () -> Unit) {
        Progress(progress) { Text("LHML配置向导", Modifier.padding(top = 15.dp)) }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("请 选 择 你 的 验 证 方 式")
                Row {
                    Button(onClick = { onItemClick(Screen.OnlineAuth)}, content = {
                        Text(text = "正版")
                    }, modifier = Modifier.padding(end = 5.dp))
                    FilledTonalButton(onClick = { onItemClick(Screen.OfflineAuth)}, content = {
                        Text(text = "离线")
                    }, modifier = Modifier.padding(end = 5.dp))
                    FilledTonalButton(onClick = { onItemClick(Screen.ThirdParty)}, content = {
                        Text(text = "其他")
                    })
                }
            }
            NextButton(
                onClick = onBack,
                { Icon(Icons.Filled.ArrowBack, "上一步") },
                Modifier.padding(all = 16.dp),
                Alignment.CenterStart
            )
        }
    }
}