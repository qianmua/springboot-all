package pres.qianmuna.kt.entity

/**
@author HJC
@date 2020/6/4  17:11
@description :
@version 1.0
谦谦君子 卑以自牧也
 */

class ProviderModel (private val id:Long?,
                     private val proCode:String?,
                     private val proName:String?,
                     private val proDesc:String?,
                     private val proContact:String?,
                     private val proPhone:String?,
                     private val proAddress:String?,
                     private val proFax:String?,
                     private val createdBy:String?,
                     private val creationDate:String?,
                     private val modifyDate:String?,
                     private val modifyBy:String? ){


    override fun toString(): String {
        return "ProviderModel(id=$id, proCode=$proCode, proName=$proName, proDesc=$proDesc, proContact=$proContact, proPhone=$proPhone, proAddress=$proAddress, proFax=$proFax, createdBy=$createdBy, creationDate=$creationDate, modifyDate=$modifyDate, modifyBy=$modifyBy)"
    }
}