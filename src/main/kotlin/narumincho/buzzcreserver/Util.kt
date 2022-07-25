package narumincho.buzzcreserver

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Bukkit
import java.util.*

fun executeCommand(commandName: String, args: Array<String>) {
    val command = Bukkit.getCommandMap().getCommand(commandName)
    if (command == null) {
        Bukkit.getLogger().warning(commandName + "というコマンドは見つからなかった")
        return
    }
    command.execute(Bukkit.getConsoleSender(), commandName, args)
}

fun <T> optionalToNullable(optional: Optional<T>): T? {
    return optional.orElse(null)
}

fun equalComponentAsPlanText(a: Component, text: String, log: Boolean = false): Boolean {
    val aText = PlainTextComponentSerializer.plainText().serialize(a)
    if (log) {
        Bukkit.getLogger().info("$aText と $text を比較 ${aText.contains(text)} だった")
    }
    return aText.contains(text)
}
