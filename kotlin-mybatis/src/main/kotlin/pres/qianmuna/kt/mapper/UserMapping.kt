package pres.qianmuna.kt.mapper

import org.apache.ibatis.annotations.*
import org.apache.ibatis.mapping.FetchType
import pres.qianmuna.kt.entity.UserModel

/**
@author HJC
@date 2020/6/4  17:26
@description :
@version 1.0
谦谦君子 卑以自牧也
 */
@Mapper
interface UserMapping {


    /**
     * 根据id 查询 用户
     */
    @Select("select * from smbms_user where id = #{id}")
    fun queryUserById(@Param("id") v1: Long): UserModel?


    @Select("""
        select u.id , contact , addressDesc , tel , postCode
        from smbms_user u , smbms_address a
        where u.id = a.userId
    """)
    fun queryUserAddress(): MutableList<Map<String , Any>>?

    @Select("""
        select u.id 
        from smbms_user u , smbms_address a
        where u.id = a.userId
       
    """)
    @Results(id = "res1" ,value = [
        Result(id = true, column = "id", property = "id"),
        Result(property = "addressModel"  ,
                column = "id" ,
                many = Many(select = "pres.qianmuna.kt.mapper.AddressMapping.queryAddressByUserid"))
    ])
    fun queryUserAddress2(): MutableList<UserModel>?


}