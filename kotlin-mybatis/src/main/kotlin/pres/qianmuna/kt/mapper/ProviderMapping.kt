package pres.qianmuna.kt.mapper

import org.apache.ibatis.annotations.*
import org.apache.ibatis.jdbc.SQL
import pres.qianmuna.kt.entity.ProviderModel
import pres.qianmuna.kt.provider.ProviderSqlUpdate

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
     * id 主键id
     */
    @Select("""
        select * from smbms_provider
        where id = #{id}
    """)
    fun queryProviderById(@Param("id")v1: Long ):ProviderModel?


    /**
     * 添加 商品
     * 添加添加。。。
     */
    @Insert("""
        insert into
        smbms_provider(proCode, proName, proDesc, proContact, proPhone, proAddress, proFax, createdBy, creationDate)
        VALUES (#{proCode},
                #{proName},
                #{proDesc},
                #{proContact}	,
               #{proPhone}	,
                #{proAddress}	,
                #{proFax},
                #{createdBy}	,
                now()
               );
    """)
    @Options( useGeneratedKeys = false , keyProperty = "id" , keyColumn = "id")
    fun addProvider(providerModel: ProviderModel):Int

    /**
     * 修改 商品
     *
     * 使用 内部类 形式 动态拼接
     *
     */
    @UpdateProvider( type = ProviderSqlUpdate::class  , method = "updateInfo")
    fun updateProvider(providerModel: ProviderModel):Int

    /**
     * 删除
     *
     * id 主键
     *
     */
    @Delete("delete from smbms_provider where id = #{id}; ")
    fun deleteProviderById(id:Long):Int




}