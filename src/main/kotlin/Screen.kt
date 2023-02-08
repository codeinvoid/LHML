import androidx.compose.runtime.*
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import component.RailItem
import page.main.MainPage
import page.main.SearchPage
import page.main.SettingPage
import page.nonconfig.*

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

    @Parcelize
    object Download: IndexPage()

    @Parcelize
    object Module: IndexPage()
}

@Composable
fun HomeRails(onExitClick: () -> Unit) {
    val navigation = remember { StackNavigation<IndexPage>() }
    var item by remember { mutableStateOf(0) }

    ChildStack(
        source = navigation,
        initialStack = { listOf(IndexPage.Home) },
        animation = stackAnimation(fade()),
    ) { index ->
        RailItem(
            { onExitClick() },
            {
                item = it
                navigation.bringToFront(getItemInt(it))
            },
            item,
        )
        when (index) {
            is IndexPage.Home -> {
                MainPage().index()
            }
            is IndexPage.Search -> {
                SearchPage().index(
                    { onExitClick() },
                    {
                        navigation.bringToFront(getItemInt(it))
                    }
                )
            }
            is IndexPage.Settings -> {
                SettingPage().index(
                    { onExitClick() },
                    {
                        navigation.bringToFront(getItemInt(it))
                    }
                )
            }
            is IndexPage.Download -> {
                SettingPage().index(
                    { onExitClick() },
                    {
                        navigation.bringToFront(getItemInt(it))
                    }
                )
            }
            is IndexPage.Module -> {
                SettingPage().index(
                    { onExitClick() },
                    {
                        navigation.bringToFront(getItemInt(it))
                    }
                )
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
