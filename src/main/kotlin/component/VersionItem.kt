package component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DataArray
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import org.to2mbn.jmccc.option.MinecraftDirectory
import org.to2mbn.jmccc.version.parsing.Versions
import utils.reader.JsonReader
import java.io.File
import java.util.*

@Composable
fun VersionItem(fab: (Boolean) -> Unit, selected: (Int) -> Unit) {
    val listState = rememberLazyListState()
    val expandedFab by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }
    fab(expandedFab)
    //TEST

    val dir = MinecraftDirectory(File("../../Documents/Minecraft/.minecraft"))
    val list = Versions
        .getVersions(dir).toList()

    //TEST
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(list.size) }
    LazyColumn(Modifier.selectableGroup().padding(start = 10.dp, end = 10.dp),state = listState) {
        items(list.size) { message ->
            val text = list[message]
            val get = Versions.resolveVersion(dir, text)
            val loader = delectLoader(get.mainClass)
            val icon = delectIcon(loader)
            val json = dir.getVersionJson(get.version)
            val loaderName = JsonReader().reader(json).patches
            val loaderNames = remember { mutableStateOf("Vanilla ${loaderName[0].version}") }

            Card(
                Modifier.padding(bottom = 10.dp)
                    .fillMaxWidth()
                    .height(80.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
            ) {
                Row(
                    Modifier
                        .fillMaxSize()
                        .selectable(
                            selected = (message == selectedOption),
                            onClick = {
                                onOptionSelected(message)
                                selected(message)
                            },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        icon, null
                    )
                    Column {

                        Text(
                            text = get.version,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(start = 16.dp)
                        )

                        if (loaderName.size > 1) {
                            loaderNames.value = "${
                                loaderName[1].id.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.getDefault()
                                    ) else it.toString()
                                }
                            } ${loaderName[1].version}"
                        }

                        Text(
                            text = loaderNames.value,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    RadioButton(
                        selected = (message == selectedOption),
                        onClick = null
                    )
                }
            }
        }
    }
}

fun delectLoader(mainClass: String): String {
    return when(mainClass) {
        "net.minecraft.client.main.Main" -> {
            "Vanilla"
        }
        "net.fabricmc.loader.impl.launch.knot.KnotClient" -> {
            "Fabric"
        }
        "cpw.mods.bootstraplauncher.BootstrapLauncher" -> {
            "Forge"
        }
        else -> {
            "Unknown"
        }
    }
}

fun delectIcon(loader: String): ImageVector {
    return when(loader) {
        "Vanilla" -> {
            Icons.Filled.GridView
        }
        "Fabric" -> {
            Icons.Filled.DataObject
        }
        "Forge" -> {
            Icons.Filled.DataArray
        }
        else -> {
            Icons.Filled.HelpOutline
        }
    }
}