package narumincho.buzzcreserver

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class NaruminchoC(private val context: Context) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        context.log("onCommand が呼ばれた")
        context.executeCommand("list", arrayOf())
        return true
    }
}
