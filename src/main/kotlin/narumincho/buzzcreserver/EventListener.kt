package narumincho.buzzcreserver

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerPortalEvent
import java.util.*
import kotlin.concurrent.schedule


class EventListener() : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        Timer("SettingUp", false).schedule(5000) {
            event.player.sendMessage("バズクリサーバーへようこそ! 自由に過ごしてね! ポータルで転送されないバグは直ったぞ! /m でメニューが開けるように開発中!")
            for (player in Bukkit.getOnlinePlayers()) {
                event.player.world.playSound(
                    event.player.location, Sound.ENTITY_PLAYER_LEVELUP,
                    1f,
                    1f
                )
            }
        }
        Bukkit.getLogger().info("参加を検知した " + event.player.name)
    }
}
