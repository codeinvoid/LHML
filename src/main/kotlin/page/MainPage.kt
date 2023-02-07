package page

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.RailItem
import component.VersionItem

class MainPage {
    @Composable
    fun index(onExitClick: (Boolean) -> Unit,
              onItemClick: (Int) -> Unit) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            RailItem({ onExitClick(it) }, { onItemClick(it) })
            versionCard()
        }
    }

    @Composable
    private fun versionCard() {
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 80.dp)) {
            Card(
                Modifier
                    .padding(bottom = 38.dp, top = 45.dp, end = 450.dp)
            ) {
                Text(
                    text = "游戏版本",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 15.dp, top = 5.dp, bottom = 5.dp)
                )
                Row {
                    VersionItem()
                }
            }
        }
    }
}
