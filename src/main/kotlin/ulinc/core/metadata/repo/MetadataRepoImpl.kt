package ulinc.core.metadata.repo

import ulinc.core.metadata.member.MetadataMember

abstract class MetadataRepoImpl:MetadataRepo {



  override fun addMember(member: MetadataMember) {

  }

  override fun deleteMember(member:MetadataMember) {

  }

  override fun getChildById(id:String):MetadataMember?{

    return null
  }

}