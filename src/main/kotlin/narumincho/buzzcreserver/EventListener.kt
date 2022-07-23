package narumincho.buzzcreserver

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerPortalEvent


class EventListener() : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.sendMessage("はじめまして...かな..? バズクリサーバーへようこそ! 自由に過ごしてね!")
        for (player in Bukkit.getOnlinePlayers()) {
            event.player.sendMessage(player.name + " が参加中")
            event.player.world.playSound(event.player, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f)
        }
        Bukkit.getLogger().info("参加を検知した " + event.player.name)
    }

    @EventHandler
    fun onEnterNetherPortal(event: PlayerPortalEvent) {
        Bukkit.getLogger().info("ポータルイベントを検知した from.world${event.from.world.name}} to.world=${event.to.world.name} playerName=${event.player.name}")
    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        if(event.to.block.type == Material.NETHER_PORTAL) {
            Bukkit.getLogger().info("ポータルにふれたようだ")
            event.player.sendMessage("ポータル修正中...")
//            event.player.world.locateNearestStructure(event.player.location, Structure.)
        }
    }
}
