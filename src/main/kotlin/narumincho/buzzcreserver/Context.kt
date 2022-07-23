package narumincho.buzzcreserver

class Context(
        val log: (message: String) -> Unit,
        val executeCommand: (commandName: String, argument: Array<String>) -> Unit
)
