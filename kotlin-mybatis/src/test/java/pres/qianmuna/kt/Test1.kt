package pres.qianmuna.kt

import org.junit.Test
import pres.qianmuna.kt.config.MybatisFactoryConfig
import pres.qianmuna.kt.entity.UserModel
import pres.qianmuna.kt.mapper.UserMapping

/**
@author HJC
@date 2020/6/4  17:03
@description :
@version 1.0
谦谦君子 卑以自牧也
 */

class Test1 {



    @Test
    fun m1(){

        //select user by id
        val sqlSession = MybatisFactoryConfig.sqlSession

        var one = sqlSession.selectOne<UserModel>("pres.qianmuna.kt.mapper.UserMapping.queryUserById", 1L)

        println(one)

    }
}

