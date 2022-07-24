package narumincho.buzzcreserver

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerJoinEvent
import java.net.URL
import java.util.*
import kotlin.concurrent.schedule


class EventListener() : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        Timer("SettingUp", false).schedule(5000) {
            event.player.sendMessage("バズクリサーバーへようこそ! 自由に過ごしてね! ポータルで転送されないバグは直ったぞ! /m でメニューが開けるように開発中!")
            event.player.sendMessage(
                Component.text(
                    "3Dマップ (bluemap)",
                    Style.style().decorate(TextDecoration.UNDERLINED).clickEvent(
                        ClickEvent.openUrl(URL("http://160.251.53.112:8100/"))
                    ).build()
                )
            )
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

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.view.title() == menuTitle) {
            event.isCancelled = true
            if (event.currentItem?.displayName() == testMenuItemName) {
                event.whoClicked.sendMessage("メニューの項目をクリックしたようだね")
                event.whoClicked.closeInventory()
            }
        }
    }
}
