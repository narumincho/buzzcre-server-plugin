package narumincho.buzzcreserver

import org.bukkit.plugin.java.JavaPlugin

class BuzzcreServerPlugin : JavaPlugin() {
    override fun onEnable() {
        logger.info("有効化できたっぽいぞ")
        val naruminchoCCommand = getCommand("naruminchoc");
        if (naruminchoCCommand == null) {
            logger.info("naruminchoc コマンドを取得できなかった");
            return;
        }
        naruminchoCCommand.setExecutor(
                NaruminchoC { message: String -> logger.info(message) }
        )
        logger.info("naruminchoCCommand.setExecutorできたっぽい");
        server.pluginManager.registerEvents(MyListener(), this)
    }

    override fun onDisable() {
    }
}
