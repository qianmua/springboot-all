package pres.qianmuna.kt.config

import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactoryBuilder

/**
@author HJC
@date 2020/6/4  16:55
@description :
@version 1.0
谦谦君子 卑以自牧也
 */

class MybatisFactoryConfig private constructor(){
    companion object{
        private val factory = SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("config/mybatis-config.xml"))

        // sqlSession 事务 自动化提交
        // 默认 false
        // 关闭后 需要 手动提交
        val sqlSession:SqlSession by lazy (LazyThreadSafetyMode.SYNCHRONIZED) { factory.openSession(false) }
        //close
        val closeSqlSession = fun() = sqlSession.close()
    }
}