package pres.qianmuna.kt.entity

import java.sql.Date

/**
@author HJC
@date 2020/6/4  17:11
@description :
@version 1.0
谦谦君子 卑以自牧也
 */

class ProviderModel (var id:Long? = null,
                     var proCode:String?= null,
                     var proName:String?= null,
                     var proDesc:String?= null,
                     var proContact:String?= null,
                     var proPhone:String?= null,
                     var proAddress:String?= null,
                     var proFax:String?= null,
                     var createdBy:String?= null,
                     var creationDate:Date? = null,
                     private var modifyDate:Date? = null,
                     var modifyBy:String? = null ){


    override fun toString(): String {
        return "ProviderModel(id=$id, proCode=$proCode, proName=$proName, proDesc=$proDesc, proContact=$proContact, proPhone=$proPhone, proAddress=$proAddress, proFax=$proFax, createdBy=$createdBy, creationDate=$creationDate, modifyDate=$modifyDate, modifyBy=$modifyBy)"
    }
}