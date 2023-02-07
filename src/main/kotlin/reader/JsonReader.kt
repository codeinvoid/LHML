package reader

import config.Config
import config.Setting
import io.github.prismwork.prismconfig.api.PrismConfig
import io.github.prismwork.prismconfig.api.config.DefaultSerializers
import java.io.File

class JsonReader {
    fun reader(file: File): Patches {
        return PrismConfig.getInstance().serialize(
            Patches::class.java,
            file,
            DefaultSerializers.getInstance().json(Patches::class.java)
        )
    }
}

val config = Config(File("./config/setting.toml")).read(Setting::class.java)
