package narumincho.buzzcreserver

import de.bluecolored.bluemap.api.BlueMapAPI
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import java.net.URL
import java.util.Timer
import java.util.logging.Logger
import kotlin.concurrent.schedule


class EventListener(private val logger: Logger) : Listener {
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
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {

        if (equalComponentAsPlanText(event.view.title(), menuTitle)) {
            val currentItemName = event.currentItem?.displayName()
            if (currentItemName == null) {
                event.isCancelled = true
                event.whoClicked.sendMessage("currentItemName が null だった...")
                return
            }
            if (equalComponentAsPlanText(currentItemName, testMenuItemName)) {
                event.isCancelled = true
                event.whoClicked.sendMessage("マーカー 一覧を出力....")
                event.whoClicked.closeInventory()
                BlueMapAPI.getInstance().ifPresent { bluemapApi ->
                    run {
                        for (markerSet in bluemapApi.markerAPI.markerSets) {
                            logger.info("markerSet.id=" + markerSet.id)
                            event.whoClicked.sendMessage(markerSet.id + "," + markerSet.label)
                            for (marker in markerSet.markers) {
                                event.whoClicked.sendMessage("- " + marker.label)
                            }
                        }
                    }
                }
            }
            if(false) {
                // これでコスト計算できそう.
                for(item in event.whoClicked.inventory) {
                    if(item.type === Material.EMERALD) {

                    }
                }
                event.whoClicked.inventory.removeItem(ItemStack(Material.EMERALD, 1))
            }
        }
    }
}
