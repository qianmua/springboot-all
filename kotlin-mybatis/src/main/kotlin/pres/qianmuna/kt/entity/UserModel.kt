package pres.qianmuna.kt.entity

/**
@author HJC
@date 2020/6/4  17:20
@description :
@version 1.0
谦谦君子 卑以自牧也
 */
data class UserModel(private val id:Long? = null,
                     private val userCode: String? = null,
                     private val userName: String? = null,
                     private val userPassword: String? = null,
                     private val gender: Int? = null,
                     private val birthday: String? = null,
                     private val phone: String? = null,
                     private val address: String? = null,
                     private val userRole: Long? = null,
                     private val createdBy: Long? = null ,
                     private val creationDate: String? = null,
                     private val modifyBy: Long? = null,
                     private val modifyDate: String? = null,
                     private val idPicPath: String? = null,
                     private val workPicPath: String?= null ,
                     private val addressModel: MutableList<AddressModel>? = null){

    override fun toString(): String {
        return "UserModel(id=$id, userCode=$userCode, userName=$userName, userPassword=$userPassword, gender=$gender, birthday=$birthday, phone=$phone, address=$address, userRole=$userRole, createdBy=$createdBy, creationDate=$creationDate, modifyBy=$modifyBy, modifyDate=$modifyDate, idPicPath=$idPicPath, workPicPath=$workPicPath, addressModel=$addressModel)"
    }

}