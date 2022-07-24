package narumincho.buzzcreserver

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

val menuTitle = Component.text("メニュー")

val testMenuItemName = Component.text("メニューの項目テスト")

class MenuCommandHandler : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            val gui = Bukkit.createInventory(sender, 9, menuTitle)

            val sampleItem = ItemStack(Material.STONE)
            sampleItem.editMeta { consumer ->
                run {
                    consumer.displayName(testMenuItemName)
                    consumer.lore(
                        listOf(
                            Component.text(sender.name + "さんのメニュー"),
                        )
                    )
                }

            }
            gui.setItem(0, sampleItem)

            sender.openInventory(gui)
        }
        return true
    }
}
