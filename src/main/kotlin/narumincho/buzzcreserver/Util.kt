package narumincho.buzzcreserver

import org.bukkit.Bukkit

fun executeCommand(commandName: String, args: Array<String>) {
    val command = Bukkit.getCommandMap().getCommand(commandName)
    if(command == null) {
        Bukkit.getLogger().warning(commandName + "というコマンドは見つからなかった")
        return
    }
    command.execute(Bukkit.getConsoleSender(), commandName, args)
}
