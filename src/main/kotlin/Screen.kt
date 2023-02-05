import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import page.OfflineAccount
import page.OnlineAccount
import page.StartPage
import page.ThirdPartyAccount

@Composable
fun MainContent() {
    val navigation = remember { StackNavigation<Screen>() }

    ChildStack(
        source = navigation,
        initialStack = { listOf(Screen.Start) },
        animation = stackAnimation(fade() + scale()),
    ) { screen ->
        when (screen) {
            is Screen.Start -> StartPage().start(onItemClick = { navigation.push(it) })
            is Screen.OnlineAuth -> OnlineAccount().model(onBack = navigation::pop,
                onItemClick= { navigation.push(Screen.OfflineAuth) })
            is Screen.OfflineAuth -> OfflineAccount().enterName(onBack = navigation::pop)
            is Screen.ThirdParty -> ThirdPartyAccount()
        }
    }
}

sealed class Screen : Parcelable {

    @Parcelize
    object Start : Screen()

    @Parcelize
    object OnlineAuth : Screen()

    @Parcelize
    object OfflineAuth : Screen()

    @Parcelize
    object ThirdParty : Screen()
}