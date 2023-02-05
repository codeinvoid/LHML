package page

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
import org.to2mbn.jmccc.auth.OfflineAuthenticator

class OfflineAccount {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun enterName(onBack: () -> Unit) {
        var uesrName by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Column {
                Text("添加一个离线帐户")
                OutlinedTextField(
                    value = uesrName,
                    onValueChange = { uesrName = it
                        isError = uesrName.isBlank()
                    },
                    label = { Text("帐户名") },
                    isError = isError
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

    @Composable
    fun NextButton(
        onClick: () -> Unit,
        icon: @Composable () -> Unit,
        modifier: Modifier,
        contentAlignment: Alignment
    ) {
        Box(Modifier.fillMaxSize(),contentAlignment = contentAlignment) {
            ExtendedFloatingActionButton(
                text = { },
                icon = icon,
                onClick = onClick,
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                modifier = modifier,
                expanded = false
            )
        }
    }

    private fun setting(userName: String) {
        OfflineAuthenticator(userName)
    }
}
