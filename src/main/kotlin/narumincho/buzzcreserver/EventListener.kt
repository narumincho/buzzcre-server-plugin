package narumincho.buzzcreserver

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import java.net.URL
import java.util.*
import java.util.logging.Logger
import kotlin.concurrent.schedule


class EventListener(private val logger: Logger) : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        Timer("SettingUp", false).schedule(5000) {
            event.player.sendMessage("バズクリサーバーへようこそ! 自由に過ごしてね!")
            event.player.sendMessage(
                Component.text(
                    "3Dマップ (bluemap)",
                    Style.style().decorate(TextDecoration.UNDERLINED).clickEvent(
                        ClickEvent.openUrl(URL("http://mc.narumincho.com:8100/"))
                    ).build()
                )
            )
            event.player.sendMessage("お知らせ")
            event.player.sendMessage("・ /m でメニューはやめました")
            event.player.sendMessage("・ BlueMap でマーカーを付けたい場所と名前があればチャットで発言してね")
            event.player.sendMessage("・ その他 なにかあればチャットで発言してね. あとで見てます")
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
    fun onAsyncChatEvent(asyncChatEvent: AsyncChatEvent) {
        logger.info("${asyncChatEvent.player.name}がチャットで発言した. 場所 ${asyncChatEvent.player.location.world} ${asyncChatEvent.player.location.x} ${asyncChatEvent.player.location.y} ${asyncChatEvent.player.location.z}")
    }
}
