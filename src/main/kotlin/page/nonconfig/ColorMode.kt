package page.nonconfig

import Screen
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.NextButton
import component.Progress
import config.Config
import config.Setting
import utils.reader.config
import java.io.File

class ColorMode {
    @Composable
    fun darkMode(
        progress: Float = 1f,
        isFollowSystem: (Boolean) -> Unit,
        isLightMode: (Boolean) -> Unit,
        onItemClick: (Screen) -> Unit
    ) {
        Progress(progress) {
            Text("LHML配置向导", Modifier.padding(top = 15.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ){
                var lightMode by remember { mutableStateOf(config.lightMode) }
                val icon: @Composable () -> Unit = if (lightMode) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Lightbulb,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                } else {
                    {
                    Icon(
                        imageVector = Icons.Rounded.DarkMode,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                    }
                }
                col(icon = { icon() }, isFollowSystem = { isFollowSystem(it) }, isLightMode = {
                    isLightMode(it)
                    lightMode = it
                })
                NextButton(
                    { onItemClick(Screen.Start) },
                    { Icon(Icons.Filled.ArrowForward, "下一步") },
                    Modifier.padding(all = 16.dp),
                    Alignment.CenterEnd
                )
            }
        }
    }

    private fun changeConfig(colorMode: Boolean, darkMode: Boolean) {
        val setting = Config(File("./config/setting.toml")).read(Setting::class.java)
        Config(File("./config/setting.toml")).write(
            Setting::class.java,
            setting.copy(colorMode = colorMode, lightMode = darkMode)
        )
    }

    @Composable
    private fun col(
        icon: @Composable () -> Unit,
        isFollowSystem: (Boolean) -> Unit,
        isLightMode: (Boolean) -> Unit,
    ) {
        var lightMode by remember { mutableStateOf(config.lightMode) }
        var followSystem by remember { mutableStateOf(config.colorMode) }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box (modifier = Modifier.width(380.dp).fillMaxWidth().padding(start = 30.dp, end = 30.dp)){
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text("亮/暗色模式")
                    Spacer(Modifier.weight(1f))
                    Switch(
                        checked = lightMode,
                        onCheckedChange = { lightMode = it
                            isLightMode(it)
                            changeConfig(followSystem,lightMode)},
                        thumbContent = icon,
                        enabled = !followSystem
                    )
                }
            }
            Box (modifier = Modifier.width(380.dp).fillMaxWidth().padding(start = 30.dp, end = 30.dp)){
                Divider()
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("跟随系统")
                    Spacer(Modifier.weight(1f))
                    Switch(
                        checked = followSystem,
                        onCheckedChange = { followSystem = it
                            isFollowSystem(it)
                            changeConfig(followSystem,lightMode)
                        }
                    )
                }
            }
        }
    }
}
