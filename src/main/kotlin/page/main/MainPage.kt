package page.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.SmsFailed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import component.VersionItem
import utils.launcher.Launcher

class MainPage {
    @Composable
    fun index() {
        var warning by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier.fillMaxSize().zIndex(0f)
        ){
            versionCard{
                warning = it
            }
        }
        Box(
            modifier = Modifier.fillMaxSize().zIndex(1f)
        ){
            Row(Modifier.align(Alignment.TopCenter).width(360.dp)) {
                AnimatedVisibility(
                    warning
                ) {
                    Snackbar(
                        action = {
                            TextButton(onClick = {
                                warning = false
                            }) {
                                Text("了解", color = MaterialTheme.colorScheme.onPrimary)
                            }
                        },
                        modifier = Modifier.padding(8.dp)
                    ){Text(text = "你还没有选择版本！")}
                }
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun versionCard(showBar: (Boolean) -> Unit) {
        var index by remember { mutableStateOf(true) }
        var selected: Int? by remember { mutableStateOf(null) }

        Row(Modifier.fillMaxSize()) {
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 80.dp)) {
                Card(
                    Modifier
                        .padding(bottom = 38.dp, top = 45.dp)
                        .width(260.dp)
                ) {
                    Text(
                        text = "游戏版本",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(start = 15.dp, top = 5.dp, bottom = 5.dp)
                    )
                    Row {
                        VersionItem({ index = it }, { selected = it })
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(end = 20.dp, bottom = 35.dp)
                .align(Alignment.Bottom)) {
                    Spacer(Modifier.weight(1f))
                ExtendedFloatingActionButton(
                    onClick = {
                        if (selected == null) {
                            showBar(true)
                        } else {
                            Launcher().run(selected)
                        }
                    },
                    expanded = index,
                    icon = {
                        if (selected == null) {
                            Icon(Icons.Filled.SmsFailed, null)
                        } else {
                            Icon(Icons.Filled.RocketLaunch, null)
                        }
                    },
                    text = {
                        if (selected == null) {
                            Text(text = "版本未选择")
                        } else {
                            Text(text = "启动游戏")
                        }
                    },
                )
            }
        }
    }
}
