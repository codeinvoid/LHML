package component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.BitmapPainter
import io.github.prismwork.prismconfig.libs.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import utils.reader.Skin
import java.awt.Image
import java.io.ByteArrayInputStream
import java.util.*

var client = OkHttpClient()
val gson = Gson()

@Composable
fun PlayerImage() {

    for (data in readData("").properties) {
        if (data.name == "textures") {
            val skin = Base64.getDecoder().decode(data.value)
        }
    }


//    Box {
//        Image(i)
//        Icon()
//    }
}

fun readData(uuid: String): Skin {
    val request: Request = Request.Builder()
        .url("https://sessionserver.mojang.com/session/minecraft/profile/$uuid")
        .build()
    client.newCall(request).execute().use { response ->
        return gson.fromJson(response.body?.string() ?: String(), Skin::class.java)
    }
}