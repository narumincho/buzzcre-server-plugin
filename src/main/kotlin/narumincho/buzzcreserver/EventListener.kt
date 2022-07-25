package narumincho.buzzcreserver

import de.bluecolored.bluemap.api.BlueMapAPI
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
            event.player.sendMessage("バズクリサーバーへようこそ! 自由に過ごしてね! /m でメニューが開けるように開発中!")
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
        menuTitle;
        if (equalComponentAsPlanText(event.view.title(), menuTitle)) {
            Bukkit.getLogger().info(event.view.player.name + "さん. メニューの項目をクリックしたようだね")
            val currentItemName = event.currentItem?.displayName()
            if (currentItemName == null) {
                event.whoClicked.sendMessage("currentItemName が null だった...")
                event.isCancelled = true
                return
            }
            if (equalComponentAsPlanText(currentItemName, testMenuItemName)) {
                event.whoClicked.sendMessage("ok")
                event.isCancelled = true
                event.whoClicked.sendMessage("マーカー 一覧")
                event.whoClicked.closeInventory()
                BlueMapAPI.getInstance().ifPresent { bluemapApi ->
                    run {
                        for (marker in bluemapApi.markerAPI.markerSets) {
                            event.whoClicked.sendMessage(marker.label)
                        }
                    }
                }
            }
        }
    }
}
