package pres.qianmuna.kt.entity

import java.sql.Date

/**
@author HJC
@date 2020/6/5  10:42
@description :
@version 1.0
谦谦君子 卑以自牧也
 */
data class BillModel (var id:Long? =0,
                     var billCode:String? = "" ,
                     var productName:String? = "" ,
                     var productDesc:String? = "",
                     var productUnit:String? = "",
                     var productCount:String? = "" ,
                     var totalPrice:Double?= 0.0 ,
                     var isPayment:Int? = 0,
                     var createdBy:String? = "",
                     var creationDate:Date? = null ,
                     var modifyBy:Long? = 0,
                     var modifyDate:String? = "",
                     var providerId:Long? = 0,
                     var providerName:Map<String,Any>? = null)