package utils.launcher

import config.Config
import jmccc.microsoft.MicrosoftAuthenticator
import jmccc.microsoft.entity.MicrosoftSession
import org.to2mbn.jmccc.launch.LauncherBuilder
import org.to2mbn.jmccc.option.LaunchOption
import org.to2mbn.jmccc.option.MinecraftDirectory
import org.to2mbn.jmccc.version.parsing.Versions
import java.io.File

class Launcher {
    fun run(verInt: Int?) {
        if (verInt != null) {
            var state = false
            val dir = MinecraftDirectory(File("../../Documents/Minecraft/.minecraft"))
            val authFile = File("./config/auth.toml")
            val session = if (authFile.exists()) {
                Config(authFile).read(MicrosoftSession::class.java)
            } else {
                MicrosoftAuthenticator.login {
                    println(it.message)
                }.session
            }
            Config(authFile).write(MicrosoftSession::class.java, session)
            val auth = MicrosoftAuthenticator.session(session) {
                state = true
                println(it.message)
            }
            if (state) {
                Config(File("./config/auth.toml")).write(MicrosoftSession::class.java, auth.session)
            }
            val version = Versions.getVersions(dir).toList()
            Versions.getVersions(dir).forEach {
                println(it)
            }
            val lunch = LauncherBuilder.create()
            lunch.build().launch(
                LaunchOption(
                    version[verInt],
                    auth,
                    dir
                ),
                Listener()
            )
        }
    }
}
