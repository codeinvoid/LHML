package launcher

import org.to2mbn.jmccc.option.MinecraftDirectory
import org.to2mbn.jmccc.version.parsing.Versions
import java.io.File

class Launcher {
    fun run() {
        val dir = MinecraftDirectory(File("../../Documents/Minecraft/.minecraft"))
        Versions.getVersions(dir).forEach {
            println(it)
        }
    }
}
