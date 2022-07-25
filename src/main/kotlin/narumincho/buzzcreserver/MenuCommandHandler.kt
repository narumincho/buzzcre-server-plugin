package narumincho.buzzcreserver

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.logging.Logger

const val menuTitle = "メニュー"

const val testMenuItemName = "bluemap の マーカー"

class MenuCommandHandler(private val logger: Logger) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender is Player) {
            logger.info(sender.name + "がメニューを開いた!")
            val gui = Bukkit.createInventory(sender, 9, Component.text(menuTitle))

            val sampleItem = ItemStack(Material.STONE)
            sampleItem.editMeta { itemMeta ->
                run {
                    itemMeta.displayName(Component.text(testMenuItemName).decorate(TextDecoration.BOLD))
                    itemMeta.setCustomModelData(1)
                    itemMeta.lore(
                        listOf(
                            Component.text("bluemap に 設定したマーカーを取得する").color(TextColor.color(200, 200, 200)),
                            Component.text("(エメラルドをコストにして設定できるようにしようかな)"),
                        )
                    )
                }

            }
            gui.setItem(0, sampleItem)

            sender.openInventory(gui)
            return true
        }
        logger.info("プレイヤー以外が /m コマンドを実行した")
        return true
    }
}
