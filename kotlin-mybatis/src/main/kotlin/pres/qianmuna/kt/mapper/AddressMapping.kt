package pres.qianmuna.kt.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import pres.qianmuna.kt.entity.AddressModel

/**
@author HJC
@date 2020/6/8  15:57
@description :
@version 1.0
谦谦君子 卑以自牧也
 */
@Mapper
interface AddressMapping {

    /**
     * 根据id 查找地址
     */
    @Select("""
        select contact , addressDesc , tel , postCode 
        from smbms_address 
        where userId = #{userId}
        """)
    fun queryAddressByUserid( userId:Long):AddressModel?

}