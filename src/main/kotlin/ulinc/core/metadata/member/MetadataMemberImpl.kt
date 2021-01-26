package ulinc.core.metadata.member

abstract class MetadataMemberImpl:MetadataMember{

    override var id: String? = null
    override var header: String? = null
    override var parent: MetadataMember? = null
    override var children : HashMap<String, MetadataMember> = HashMap<String, MetadataMember>()

    constructor()

    override fun GetChildById(id:String):MetadataMember?{

        return null
    }

}