package pres.qianmuna.kt



/**
@author HJC
@date 2020/6/4  16:39
@description :
@version 1.0
谦谦君子 卑以自牧也
 */


fun main() {

    val v1:Array<Char> = arrayOf('A' , 'B' , 'C')

    val v2:Array<Byte> = Array(3){ v1[it].toByte() }

    v2.forEach { println(it) }



}
