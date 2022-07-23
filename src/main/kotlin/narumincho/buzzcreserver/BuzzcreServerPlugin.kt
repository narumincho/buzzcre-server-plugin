package narumincho.buzzcreserver

import org.bukkit.plugin.java.JavaPlugin

class BuzzcreServerPlugin : JavaPlugin() {
    override fun onEnable() {
        logger.info("有効化できたっぽいぞ")
        val naruminchoCCommand = getCommand("naruminchoc");
        if (naruminchoCCommand == null) {
            logger.info("naruminchoc コマンドを取得できなかった");
            return;
        }
        val context = Context(
                log = { message -> logger.info(message) },
                executeCommand = { commandName, argument ->
                    run {
                        val command = server.commandMap.getCommand(commandName)
                        if(command == null) {
                            logger.warning(commandName+"というコマンドは見つからなかった...")
                            return@run
                        }
                        command.execute(
                                server.consoleSender,
                                commandName,
                                argument
                        )
                    }

                }
        )

        naruminchoCCommand.setExecutor(NaruminchoC(context))
        logger.info("naruminchoCCommand.setExecutorできたっぽい");
        server.pluginManager.registerEvents(MyListener(context), this)
    }

    override fun onDisable() {
    }
}
