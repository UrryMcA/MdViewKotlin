package ulinc.ui.mdview.command

class CommandProviderImpl:CommandProvider {

    private var commands = HashMap<String, HashMap<String, String>>()

    override fun addCommandList(id: String, list: HashMap<String, String>) {
        commands.put(id,list)
    }

    override fun addCommand(id:String ,command:MdCommand){

        var current: HashMap<String, String>?

        current = commands.get(id)
        if (current == null) {
            current = HashMap<String, String>()
            commands.put(id,current)
        }

        if (current != null) {
            current.put(command.id, command.action)
        }
    }

    override fun getCommandList(id: String): HashMap<String, String> {
        return commands.get(id)!!
    }

}