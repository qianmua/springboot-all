package pres.qianmuna.mbp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Date;


/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/6
 * @time 20:01
 */
@Slf4j
@Component
public class MybatisFieldHandler implements MetaObjectHandler {

    /**
     * 插入
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        /**
         * get value from java bean by propertyName
         *
         * @param fieldName  java bean property name
         * @param metaObject parameter wrapper
         * @return 字段值
         */
        this.setFieldValByName("createTime" , new Date(new java.util.Date().getTime()) , metaObject);
        this.setFieldValByName("updateTime" , new Date(new java.util.Date().getTime()) , metaObject);
    }

    /**
     * 更新
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        this.setFieldValByName("updateTime" , new Date(new java.util.Date().getTime()) , metaObject);
    }
}
