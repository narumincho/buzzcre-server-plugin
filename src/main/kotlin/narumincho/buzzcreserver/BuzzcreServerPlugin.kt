package narumincho.buzzcreserver

import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin

class BuzzcreServerPlugin : JavaPlugin() {
    override fun onEnable() {
        logger.info("BuzzcreServerPlugin 初期化中....")
        for (world in server.worlds) {
            logger.info("ワールド情報...")
            logger.info(world.name)
            logger.info(world.environment.name)
            logger.info(world.spawnLocation.toString())
            logger.info(world.seed.toString())
            setWorldBorder(world)
        }
        server.pluginManager.registerEvents(EventListener(logger), this)

        logger.info("BuzzcreServerPlugin 初期化完了!")
    }

    override fun onDisable() {
        logger.info("BuzzcreServerPlugin onDisable が呼ばれた!")
    }

    private fun setWorldBorder(world: World) {
        val worldBorderSize = getWorldBorder(world.name)
        if (worldBorderSize == null) {
            logger.info("ワールドボーダー指定していないワールドがあった " + world.name)
            return
        }
        world.worldBorder.setSize(worldBorderSize, 0)
    }
}


private fun getWorldBorder(worldName: String): Double? {
    val worldWorldBorderSize = 6000.0
    return when (worldName) {
        "world" -> worldWorldBorderSize
        "world_nether" -> worldWorldBorderSize / 8
        "world_the_end" -> 10000.0
        else -> null
    }
}
