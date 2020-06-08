package pres.qianmuna.kt.entity

import java.sql.Date

/**
@author HJC
@date 2020/6/4  17:20
@description :
@version 1.0
谦谦君子 卑以自牧也
 */
data class AddressModel(private val id:Long? = null,
                        private val contact: String? = null,
                        private val addressDesc: String? = null,
                        private val postCode: String? = null,
                        private val tel: String? = null,
                        private val createdBy: String? = null,
                        private val creationDate: Date? = null,
                        private val modifyBy: String? = null,
                        private val modifyDate: Date? = null,
                        private val userId: Long? = null){

    override fun toString(): String {
        return "AddressModel(id=$id, contact=$contact, addressDesc=$addressDesc, postCode=$postCode, tel=$tel, createdBy=$createdBy, creationDate=$creationDate, modifyBy=$modifyBy, modifyDate=$modifyDate, userId=$userId)"
    }
}