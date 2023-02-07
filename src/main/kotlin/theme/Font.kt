package theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

val notoSans = FontFamily(
    Font(
        resource = "font/NotoSansSC-Black.otf",
        weight = FontWeight.W900,
        style = FontStyle.Normal
    ),
    Font(
        resource = "font/NotoSansSC-Blod.otf",
        weight = FontWeight.W700,
        style = FontStyle.Normal
    ),
    Font(
        resource = "font/NotoSansSC-Light.otf",
        weight = FontWeight.W300,
        style = FontStyle.Normal
    ),
    Font(
        resource = "font/NotoSansSC-Medium.otf",
        weight = FontWeight.W500,
        style = FontStyle.Normal
    ),
    Font(
        resource = "font/NotoSansSC-Regular.otf",
        weight = FontWeight.W400,
        style = FontStyle.Normal
    ),
    Font(
        resource = "font/NotoSansSC-Thin.otf",
        weight = FontWeight.W100,
        style = FontStyle.Normal
    )
)
