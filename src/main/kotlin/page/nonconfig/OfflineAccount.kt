package page.nonconfig

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.NextButton
import component.Progress
import org.to2mbn.jmccc.auth.OfflineAuthenticator

class OfflineAccount {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun enterName(onBack: () -> Unit, progress: Float) {
        Progress(progress)
        var uesrName by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("添加一个离线帐户")
                OutlinedTextField(
                    value = uesrName,
                    onValueChange = { uesrName = it
                        isError = uesrName.isBlank()
                    },
                    label = { Text("帐户名") },
                    isError = isError,
                    supportingText = {
                        if (isError) {
                            Text("不能为空")
                        }
                    }
                )
            }
            NextButton(
                { if (uesrName.isNotBlank()) setting(uesrName) },
                { Icon(Icons.Filled.ArrowForward, "下一步") },
                Modifier.padding(all = 16.dp),
                Alignment.CenterEnd
            )
            NextButton(
                onClick = onBack,
                { Icon(Icons.Filled.ArrowBack, "上一步") },
                Modifier.padding(all = 16.dp),
                Alignment.CenterStart
            )
        }
    }

    private fun setting(userName: String) {
        println(OfflineAuthenticator(userName).auth().uuid)
    }
}
