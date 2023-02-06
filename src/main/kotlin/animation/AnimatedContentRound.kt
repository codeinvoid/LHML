package animation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentRound(target: Boolean, content: @Composable() AnimatedVisibilityScope.(targetState: Boolean) -> Unit) {
    AnimatedContent(
        targetState = target,
        transitionSpec = {
            if (targetState > initialState) {
                slideInVertically { height -> height } + fadeIn() with
                        slideOutVertically { height -> -height } + fadeOut()
            } else {
                slideInVertically { height -> -height } + fadeIn() with
                        slideOutVertically { height -> height } + fadeOut()
            }.using(
                SizeTransform(clip = false)
            )
        }, content = content)
}