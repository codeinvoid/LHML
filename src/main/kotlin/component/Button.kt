package component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


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