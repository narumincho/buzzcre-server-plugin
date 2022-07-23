package narumincho.buzzcreserver

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class NaruminchoC(val log: (message: String) -> Unit) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        log("onCommand が呼ばれた")
        return true
    }
}
