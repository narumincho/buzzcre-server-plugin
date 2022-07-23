package narumincho.buzzcreserver

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent


class MyListener: Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent?) {
        if(event == null) {
            return;
        }
        event.player.sendMessage("バズクリサーバーへようこそ! 自由に過ごしてね!")
    }
}
