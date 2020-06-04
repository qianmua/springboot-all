package pres.qianmuna.kt.mapper

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import pres.qianmuna.kt.entity.ProviderModel

/**
@author HJC
@date 2020/6/4  17:36
@description :
@version 1.0
谦谦君子 卑以自牧也
 */
@Mapper
interface ProviderMapping {

    /**
     * 根据id 查找提供者
     */
    @Select("""
        select * from smbms_provider
        where id = #{id}
    """)
    fun queryProviderById(@Param("id")v1: Long ):ProviderModel?

}