package pres.qianmuna.kt.entity

/**
@author HJC
@date 2020/6/4  17:20
@description :
@version 1.0
谦谦君子 卑以自牧也
 */
data class UserModel(private val id:Long?,
                     private val userCode: String?,
                     private val userName: String?,
                     private val userPassword: String?,
                     private val gender: Int?,
                     private val birthday: String?,
                     private val phone: String?,
                     private val address: String?,
                     private val userRole: Long?,
                     private val createdBy: Long?,
                     private val creationDate: String?,
                     private val modifyBy: Long?,
                     private val modifyDate: String?,
                     private val idPicPath: String?,
                     private val workPicPath: String? ){


    override fun toString(): String {
        return "UserModel(id=$id, userCode=$userCode, userName=$userName, userPassword=$userPassword, gender=$gender, birthday=$birthday, phone=$phone, address=$address, userRole=$userRole, createdBy=$createdBy, creationDate=$creationDate, modifyBy=$modifyBy, modifyDate=$modifyDate, idPicPath=$idPicPath, workPicPath=$workPicPath)"
    }
}