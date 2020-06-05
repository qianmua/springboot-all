package pres.qianmuna.kt.provider

import org.apache.ibatis.jdbc.SQL
import pres.qianmuna.kt.entity.ProviderModel

/**
@author HJC
@date 2020/6/5  14:12
@description :
@version 1.0
谦谦君子 卑以自牧也
 */

open class ProviderSqlUpdate{
    fun updateInfo(providerModel: ProviderModel): String {
        var sql = SQL()
        sql.UPDATE("smbms_provider").SET("modifyDate = now()")
        when{ providerModel.proCode != null -> sql.SET("proCode = #{proCode}") }
        when{ providerModel.proName != null -> sql.SET("proName = #{proName}") }
        when{ providerModel.proDesc != null -> sql.SET("proDesc = #{proDesc}") }
        when{ providerModel.proContact != null -> sql.SET("proContact = #{proContact}") }
        when{ providerModel.proPhone != null -> sql.SET("proPhone = #{proPhone}") }
        when{ providerModel.proAddress != null -> sql.SET("proAddress = #{proAddress}") }
        when{ providerModel.proFax != null -> sql.SET("proFax = #{proFax}") }
        sql.WHERE("id = #{id}")

        return sql.toString()
    }
    open fun m1(map: Map<String , Any>):String? = "hello"
}