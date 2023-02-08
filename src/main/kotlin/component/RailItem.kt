package component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RailItem(onExitClick: (Boolean) -> Unit, onItemClick: (Int) -> Unit, selected: Int) {
    var selectedItem by remember { mutableStateOf(selected) }
    val items = listOf("主页", "查找", "设置")
    val icons = listOf(Icons.Filled.Rocket, Icons.Filled.Search, Icons.Filled.Settings)

    NavigationRail(Modifier.fillMaxHeight()) {
        Column(Modifier.padding(top = 45.dp)) {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    icon = { Icon(icons[index], contentDescription = item) },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = {
                        selectedItem = index
                        onItemClick(index)
                    }
                )
            }
        }
        Spacer(Modifier.weight(1f))
        Column(Modifier.padding(bottom = 45.dp)) {
            NavigationRailItem(
                icon = { Icon(Icons.Filled.ExitToApp, contentDescription = "退出") },
                label = { Text("退出") },
                selected = false,
                onClick = { onExitClick(true) }
            )
        }
    }
}
