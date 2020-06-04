package pres.qianmuna.kt.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
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


}