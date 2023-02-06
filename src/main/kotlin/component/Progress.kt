package component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Progress(progress: Float, unit: @Composable () -> Unit = {}) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth())
        unit.invoke()
    }
}