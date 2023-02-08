package page.nonconfig

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import component.NextButton
import component.Progress
import jmccc.microsoft.MicrosoftAuthenticator
import kotlinx.coroutines.*
import java.awt.Desktop
import java.awt.Toolkit
import java.awt.datatransfer.Clipboard
import java.awt.datatransfer.StringSelection
import java.net.URI
import java.util.*

class OnlineAccount {
    @OptIn(ExperimentalUnitApi::class)
    @Composable
    fun model(onItemClick: () -> Unit, onBack: () -> Unit, progress: Float) {
        var tips by remember { mutableStateOf("") }
        var respone by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }
        Progress(progress) { Text("LHML配置向导", Modifier.padding(top = 15.dp)) }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Text("添加一个正版帐户")
                Button(onClick = {
                    response({tips = it},{respone = it})
                    active = true
                }, Modifier.align(alignment = Alignment.CenterHorizontally)) {
                    Text(text = "授权")
                }
                AnimatedVisibility(visible = active) {
                    Column {
                        Text(tips, Modifier.align(alignment = Alignment.CenterHorizontally),
                            style = TextStyle(fontSize = TextUnit(
                                1F,
                                TextUnitType(type = 1)))
                        )
                        Text(respone, Modifier.align(alignment = Alignment.CenterHorizontally),
                            style = TextStyle(fontSize = TextUnit(
                                1F,
                                TextUnitType(type = 1)))
                        )
                    }
                }
            }

            NextButton(
                { onItemClick() },
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

    private fun login(isSuccess:  (Boolean) -> Unit) {
        MicrosoftAuthenticator.setClientId("9b5b8e4a-f397-49bb-91b8-a30723bccc34")
        val clipboard: Clipboard = Toolkit.getDefaultToolkit().systemClipboard
        try {
            val session = MicrosoftAuthenticator.login{
                println(it.message)
                openInBrowser(URI(it.verificationUri))
                clipboard.setContents(StringSelection(it.userCode), null)
            }
            if (session.auth().uuid != null) {
                isSuccess(true)
            }
            println(session.session.minecraftAccessToken)
        } catch (e: Exception) {
            isSuccess(false)
        }
    }

    private fun openInBrowser(uri: URI) {
        val osName by lazy(LazyThreadSafetyMode.NONE) { System.getProperty("os.name").lowercase(Locale.ROOT) }
        val desktop = Desktop.getDesktop()
        when {
            Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE) -> desktop.browse(uri)
            "mac" in osName -> Runtime.getRuntime().exec("open $uri")
            "nix" in osName || "nux" in osName -> Runtime.getRuntime().exec("xdg-open $uri")
            else -> throw RuntimeException("cannot open $uri")
        }
    }

    private fun response(tips: (String) -> Unit, reponse: (String) -> Unit) {
        CoroutineScope(
            context = Dispatchers.Default
        ).launch {
            withTimeoutOrNull(10*1000) {
                tips("代码已复制到剪贴板")
                login { ot ->
                    if (!ot) reponse("验证失败") else reponse("验证成功")
                }
            }
        }
    }
}