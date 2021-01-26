package ulinc.core.metadata.member

interface MetadataMember {

    var id: String?
    var header: String?
    var parent: MetadataMember?
    var children : HashMap<String, MetadataMember>

    fun GetChildById(id:String):MetadataMember?


}
