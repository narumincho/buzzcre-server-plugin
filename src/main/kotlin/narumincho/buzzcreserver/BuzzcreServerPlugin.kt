package narumincho.buzzcreserver

import org.bukkit.plugin.java.JavaPlugin

class BuzzcreServerPlugin : JavaPlugin() {
    override fun onEnable() {
        logger.info("BuzzcreServerPlugin 初期化中....")
        val menuCommand = getCommand("m")
        if (menuCommand == null) {
            logger.info("m コマンドを取得できなかった")
            return
        }
        for(world in server.worlds) {
            logger.info("ワールド情報...")
            logger.info(world.name)
            logger.info(world.environment.name)
            logger.info(world.spawnLocation.toString())
        }

        menuCommand.setExecutor(MenuCommandHandler())
        server.pluginManager.registerEvents(EventListener(), this)
        logger.info("BuzzcreServerPlugin 初期化完了!")
    }

    override fun onDisable() {
    }
}
