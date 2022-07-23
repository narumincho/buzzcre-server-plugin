package narumincho.buzzcreserver

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class MenuCommandHandler : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        Bukkit.getLogger().info("m が呼ばれた")
        sender.sendMessage(sender.name + "さん. /m の メニュー機能は開発中なんだ")
        executeCommand("list", arrayOf())
        return true
    }
}
