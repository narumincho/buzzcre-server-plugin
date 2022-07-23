package narumincho.buzzcreserver

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerPortalEvent


class MyListener(private val context: Context) : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.sendMessage("はじめまして...かな..? バズクリサーバーへようこそ! 自由に過ごしてね!")
        context.log("初参加を検知した " + event.player.name)
    }

    @EventHandler
    fun onPlayerLogIn(event: PlayerLoginEvent) {
        event.player.sendMessage("バズクリサーバーへようこそ!")
        context.log("ログインを検知した " + event.player.name)
    }

    @EventHandler
    fun onEnterNetherPortal(event: PlayerPortalEvent) {
        context.log("ポータルイベントを検知した from.world${event.from.world.name}} to.world=${event.to.world.name} playerName=${event.player.name}")
    }
}
