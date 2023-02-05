package page

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
    fun model(onItemClick: () -> Unit, onBack: () -> Unit) {
        var tips by remember { mutableStateOf("") }
        var respone by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Text("添加一个正版帐户")
                Button(onClick = {
                    response(
                        tips = {
                            tips = it
                        }
                    ) {
                        respone = it
                    }
                    active = true
                }, Modifier.align(alignment = Alignment.CenterHorizontally)) {
                    Text(text = "点击授权")
                }
                AnimatedVisibility(visible = active) {
                    if (active) {
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
            }

            OfflineAccount().NextButton(
                { onItemClick() },
                { Icon(Icons.Filled.ArrowForward, "下一步") },
                Modifier.padding(all = 16.dp),
                Alignment.CenterEnd
            )
            OfflineAccount().NextButton(
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
        val session = MicrosoftAuthenticator.login{
            println(it.message)
            openInBrowser(URI(it.verificationUri))
            clipboard.setContents(StringSelection(it.userCode), null)
        }
        if (session.auth().uuid != null) {
            isSuccess(true)
        }
        println(session.session.microsoftRefreshToken)
        println(session.session.microsoftAccessToken)
        println(session.session.xboxUserId)
        println(session.session.minecraftAccessToken)

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

    private fun response(tips: (String) -> Unit, respone: (String) -> Unit) {
        CoroutineScope(
            context = Dispatchers.Default
        ).launch {
            val success = withTimeoutOrNull(300*1000) {
                return@withTimeoutOrNull suspendCancellableCoroutine<Boolean> {
                    tips("代码已复制到剪贴板")
                    login(isSuccess = { ot ->
                        it.resume(ot) {}
                    })
                }
            }
            respone(when(success) {
                null -> {
                    "验证超时"
                }

                false -> {
                    "验证失败"
                }

                true -> {
                    "验证成功"
                }
            })
        }
    }
}