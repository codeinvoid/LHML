import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.*
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import page.*

@Composable
fun MainContent(
    isFollowSystem: (Boolean) -> Unit,
    isLightMode: (Boolean) -> Unit
) {
    val navigation = remember { StackNavigation<Screen>() }

    ChildStack(
        source = navigation,
        initialStack = { listOf(Screen.PickColor) },
        animation = stackAnimation(slide()),
    ) { screen ->
        when (screen) {
            is Screen.Start -> StartPage().start(
                onItemClick = { navigation.push(it) },
                0f,
                onBack = navigation::pop
            )
            is Screen.PickColor -> ColorMode().darkMode(
                isFollowSystem = {isFollowSystem(it)},
                isLightMode = {isLightMode(it)},
                onItemClick = { navigation.push(it) }
            )
            is Screen.OnlineAuth -> OnlineAccount().model(
                onBack = navigation::pop,
                onItemClick= { navigation.push(Screen.OfflineAuth) },
                progress = 0.5f
            )
            is Screen.OfflineAuth -> OfflineAccount().enterName(onBack = navigation::pop, progress = 0.5f)
            is Screen.ThirdParty -> ThirdPartyAccount().thirdTodo(onBack = navigation::pop)
        }
    }
}

sealed class Screen : Parcelable {
    @Parcelize
    object PickColor : Screen()

    @Parcelize
    object Start : Screen()

    @Parcelize
    object OnlineAuth : Screen()

    @Parcelize
    object OfflineAuth : Screen()

    @Parcelize
    object ThirdParty : Screen()
}

sealed class IndexPage : Parcelable {
    @Parcelize
    object Home: IndexPage()

    @Parcelize
    object Search: IndexPage()

    @Parcelize
    object Settings: IndexPage()
}

@Composable
fun HomeRails() {
    val navigation = remember { StackNavigation<IndexPage>() }

    ChildStack(
        source = navigation,
        initialStack = { listOf(IndexPage.Home) },
        animation = stackAnimation(slide()),
    ) { index ->
        when (index) {
            is IndexPage.Home -> MainPage().index(onExitClick = {  }, onItemClick = { navigation.push(getItemInt(it))})
//            is IndexPage.Search -> StartPage().start(
//            onItemClick = { navigation.push(getItemInt(it)) },
//            0f,
//            onBack = navigation::pop
//            )
//
//            is IndexPage.Settings -> StartPage().start(
//                onItemClick = { navigation.push(it) },
//                0f,
//                onBack = navigation::pop
//            )
            else -> {
                println()
            }
        }
    }
}

fun getItemInt(item: Int) : IndexPage {
    return when(item) {
        0 -> {
            IndexPage.Home
        }
        1 -> {
            IndexPage.Search
        }
        2 -> {
            IndexPage.Settings
        }
        else -> {
           IndexPage.Home
        }
    }
}
