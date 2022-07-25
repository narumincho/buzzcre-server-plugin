package narumincho.buzzcreserver

import com.flowpowered.math.vector.Vector3d
import de.bluecolored.bluemap.api.BlueMapAPI
import de.bluecolored.bluemap.api.marker.Line
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin
import java.awt.Color

class BuzzcreServerPlugin : JavaPlugin() {
    override fun onEnable() {
        logger.info("BuzzcreServerPlugin 初期化中....")
        val menuCommand = getCommand("m")
        if (menuCommand == null) {
            logger.info("m コマンドを取得できなかった")
            return
        }
        for (world in server.worlds) {
            logger.info("ワールド情報...")
            logger.info(world.name)
            logger.info(world.environment.name)
            logger.info(world.spawnLocation.toString())
            logger.info(world.seed.toString())
            setWorldBorder(world)
        }

        menuCommand.setExecutor(MenuCommandHandler())
        server.pluginManager.registerEvents(EventListener(), this)

        logger.info("BuzzcreServerPlugin 初期化完了!")
    }

    override fun onDisable() {
        logger.info("BuzzcreServerPlugin onDisable が呼ばれた!")
    }
}


private fun setWorldBorder(world: World) {
    val worldBorderSize = getWorldBorder(world.name)
    if (worldBorderSize == null) {
        Bukkit.getLogger().info("ワールドボーダー指定していないワールドがあった " + world.name)
        return
    }
    world.worldBorder.setSize(worldBorderSize, 0)

    BlueMapAPI.onEnable { bluemapApi ->
        setWorldBorderInBluemap(world, worldBorderSize, bluemapApi)
    }
}

private fun setWorldBorderInBluemap(world: World, worldBorderSize: Double, bluemapApi: BlueMapAPI) {
    val markerId = world.name + "WorldBorder"
    val bluemapWorld = optionalToNullable(bluemapApi.getMap(world.name))
    if (bluemapWorld == null) {
        Bukkit.getLogger().info("Bluemap のワールド名が思ったのと, フォルダ名が違かった folderName=" + world.name)
    }
    val markerSet =
        optionalToNullable(bluemapApi.markerAPI.getMarkerSet(markerId))
            ?: bluemapApi.markerAPI.createMarkerSet(markerId)

    val marker = markerSet.createLineMarker(
        markerId + "Marker",
        bluemapWorld,
        Vector3d(worldBorderSize, 100.0, worldBorderSize),
        Line(
            Vector3d(-worldBorderSize, 100.0, -worldBorderSize),
            Vector3d(worldBorderSize, 100.0, -worldBorderSize),
            Vector3d(worldBorderSize, 100.0, worldBorderSize),
            Vector3d(-worldBorderSize, 100.0, worldBorderSize),
            Vector3d(-worldBorderSize, 100.0, -worldBorderSize)
        )
    )
    marker.lineColor = Color.RED
    marker.detail = "これより外側には行けない"
    marker.label = "ワールドボーダー"

    bluemapApi.markerAPI.save()
    Bukkit.getLogger().info("Bluemap のワールドボーダーを設定した $marker")
}

private fun getWorldBorder(worldName: String): Double? {
    val worldWorldBorderSize = 3000.0
    return when (worldName) {
        "world" -> worldWorldBorderSize
        "world_nether" -> worldWorldBorderSize / 8
        "world_the_end" -> 5000.0
        else -> null
    }
}
