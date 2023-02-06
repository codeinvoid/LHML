package page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.NextButton

class ThirdPartyAccount {
    @Composable
    fun thirdTodo(onBack: () -> Unit) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) { Text("画的饼,以后再做", Modifier.padding(top = 15.dp)) }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                NextButton(
                    onClick = onBack,
                    { Icon(Icons.Filled.ArrowBack, "上一步") },
                    Modifier.padding(all = 16.dp),
                    Alignment.CenterStart
                )
            }
        }
    }
}