package pres.qianmuna.kt

import org.junit.Test
import pres.qianmuna.kt.config.MybatisFactoryConfig
import pres.qianmuna.kt.entity.BillModel
import pres.qianmuna.kt.entity.ProviderModel
import pres.qianmuna.kt.entity.UserModel
import pres.qianmuna.kt.mapper.BillMapping
import pres.qianmuna.kt.mapper.ProviderMapping
import pres.qianmuna.kt.mapper.UserMapping

/**
@author HJC
@date 2020/6/4  17:03
@description :
@version 1.0
谦谦君子 卑以自牧也
 */

class Test1 {


    /**
     * 查找
     */
    @Test
    fun m1(){

        //select user by id
        val sqlSession = MybatisFactoryConfig.sqlSession

        val mapper = sqlSession.getMapper(BillMapping::class.java)

        val mutableList = mapper.queryBillByNameAndProviderIdAndIsPay("%油%", 7, 2)

        println(mutableList)

        MybatisFactoryConfig.closeSqlSession

    }

    /**
     * 添加
     */
    @Test
    fun m2(){

        //select user by id
        val sqlSession = MybatisFactoryConfig.sqlSession

        val mapper = sqlSession.getMapper(ProviderMapping::class.java)

        val provier = ProviderModel(0,"1","1",
                "1","1","1","1","1","1")
        var i = mapper.addProvider(provier)

        //
        //提交啊
        sqlSession.commit()

        println(i)

        MybatisFactoryConfig.closeSqlSession

    }

    /**
     * 修改
     */
    @Test
    fun m3(){

        //select user by id
        val sqlSession = MybatisFactoryConfig.sqlSession

        val mapper = sqlSession.getMapper(ProviderMapping::class.java)

        val provier = ProviderModel(21,"8888","666",
                "7777","1","1","1","1","1")
        var i = mapper.updateProvider(provier)

        //
        //提交啊
        sqlSession.commit()

        println(i)

        MybatisFactoryConfig.closeSqlSession

    }

    /**
     * 删除
     */
    @Test
    fun m4(){

        //select user by id
        val sqlSession = MybatisFactoryConfig.sqlSession

        val mapper = sqlSession.getMapper(ProviderMapping::class.java)

        val i = mapper.deleteProviderById(21L)

        //提交啊
        sqlSession.commit()

        println(i)

        MybatisFactoryConfig.closeSqlSession

    }

    /**
     * 查找用户 地址
     */
    @Test
    fun m5(){

        //select user by id
        val sqlSession = MybatisFactoryConfig.sqlSession

        val mapper = sqlSession.getMapper(UserMapping::class.java)

        var list = mapper.queryUserAddress2()

        list?.forEach { println(it) }

        //提交啊


        MybatisFactoryConfig.closeSqlSession

    }
}

