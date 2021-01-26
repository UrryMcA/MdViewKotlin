package ulinc.core.metadata.repo

import ulinc.core.metadata.member.MetadataMember

interface MetadataRepo{

    fun addMember(member: MetadataMember)
    fun deleteMember(member:MetadataMember)

    fun getChildById(id:String):MetadataMember?

}
