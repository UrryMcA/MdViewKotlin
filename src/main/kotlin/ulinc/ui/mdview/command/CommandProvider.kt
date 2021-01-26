package ulinc.ui.mdview.command

interface CommandProvider {

    fun addCommandList(id:String ,list:HashMap<String,String>)
    fun addCommand(id:String ,command:MdCommand)
    fun getCommandList(id:String):HashMap<String,String>

}