package launcher

import config.Config
import jmccc.microsoft.MicrosoftAuthenticator
import jmccc.microsoft.entity.MicrosoftSession
import org.to2mbn.jmccc.launch.Launcher
import org.to2mbn.jmccc.launch.LauncherBuilder
import org.to2mbn.jmccc.option.LaunchOption
import org.to2mbn.jmccc.option.MinecraftDirectory
import java.io.File

class Launcher {
    fun run() {
        val dir = MinecraftDirectory(File("../../Documents/Minecraft/.minecraft"))
        println(dir.absolutePath)
        val luncher: Launcher = LauncherBuilder.buildDefault();
        val session= Config(File("./config/auth.toml")).read(
            MicrosoftSession::class.java
        )

        luncher.launch(LaunchOption("1.19.2", MicrosoftAuthenticator.session(session) {
            println(it.message)
        }, dir))
    }
}