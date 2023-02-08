package page.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import component.RailItem

class DownloadPage {
    @Composable
    fun index(onExitClick: () -> Unit, item: (Int) -> Unit) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            RailItem({ onExitClick() }, { item(it) }, 3)

        }
    }
}