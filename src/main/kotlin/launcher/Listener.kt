package launcher

import org.to2mbn.jmccc.launch.ProcessListener
import java.util.logging.Logger

class Listener : ProcessListener {
    private val logger: Logger = Logger.getLogger("Minecraft")

    override fun onLog(log: String?) {
        logger.info(log)

    }

    override fun onErrorLog(log: String?) {
        logger.warning(log)
    }

    override fun onExit(code: Int) {
        if (code == 0) {
            println(code)
        }
    }
}