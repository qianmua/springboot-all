package pres.qianmuna.kt.mapper

import org.apache.ibatis.annotations.*
import pres.qianmuna.kt.entity.BillModel

/**
@author HJC
@date 2020/6/5  10:45
@description :
@version 1.0
谦谦君子 卑以自牧也
 */
@Mapper
interface BillMapping {

    /**
     * 按照条件查询 订单表
     * 商品名称
     * 供应商id
     * 是否付款
     *
     * //结果列
     * 订单编号
     * 商品名称
     * 供应商名称
     * 账单金额
     * 是否付款
     * 创建时间
     *
     */
    @Select("""
        select billCode,productName,proName,totalPrice,isPayment, smbms_bill.creationDate
        from smbms_bill , smbms_provider
        where productName like #{productName} 
          and providerId = #{id} 
          and isPayment = #{isPayment} 
          and smbms_bill.providerId = smbms_provider.id;
    """)
    /*@Results( id = "queryBillByNameAndProviderIdAndIsPay" , value = {
        @Result(property = "billCode" , column = "billCode" ),
        @Result(property = "productName" , column = "productName" ),
        @Result(property = "proName" , column = "proName" ),
        @Result(property = "totalPrice" , column = "totalPrice" ),
        @Result(property = "isPayment" , column = "isPayment" ),
        @Result(property = "isPayment" , column = "isPayment" ),
        @Result(property = "isPayment" , column = "isPayment" )
    })*/
    fun queryBillByNameAndProviderIdAndIsPay(@Param("productName")v1: String,
                                             @Param("id")v2: Long ,
                                             @Param("isPayment") v3:Int):BillModel
}