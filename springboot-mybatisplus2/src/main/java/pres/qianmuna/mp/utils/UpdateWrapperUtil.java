package pres.qianmuna.mp.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.commons.lang3.StringUtils;
import pres.qianmuna.mp.entity.Users2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/8/16  10:33
 */
public class UpdateWrapperUtil {

    public static void main(String[] args) {
        UpdateWrapper<Class<Users2>> updateWrapper = Wrappers.update(Users2.class);
        Users2 users2 = new Users2();
        users2.setUid("1");
        users2.setAddress("pu dong xin qu");
        users2.setName("Jin");
        Map<String , Object> wrapper = new UpdateWrapperUtil().getUpdateWrapper(users2);
        if (!wrapper.isEmpty()) {
            wrapper.forEach(updateWrapper::set);
        }
    }

    public <T> Map<String , Object> getUpdateWrapper(T t) {
        Map<SFunction<T , ?> , Object> fieldUpdateMap = new HashMap<>();
        List<SFunction<T , ?>> fieldUpdateList = null;
        Map<String , Object> map = null;
        Class<?> tClass = t.getClass();
        // 内部类,如果是内部类需要去除掉外部类的字段引用
        boolean isMemberClass = tClass.isMemberClass();

        for (Field field : tClass.getDeclaredFields()) {
            // set 指定字段集
            field.setAccessible(true);
            try {
                //  这里 判断对基本类型无效
                // 只取到 不为空的 字段
                if (field.get(t) != null && StringUtils.isNotBlank(field.get(t).toString())) {
                    // 判断是内部类
                    if (isMemberClass){
                        // 过滤掉 final 修饰字段(内部内引用是final的)
                        // 特殊字段(final) 这里不做处理
                        if (Modifier.isFinal(field.getModifiers())) continue;
                    }
                    if (checkBaseTypeContinue(field)) continue ;

                    // 去掉排除的字段 TableField(exist = false)
                    TableField annotation = field.getAnnotation(TableField.class);
                    if (Objects.nonNull(annotation)){
                        if (!annotation.exist()) continue;
                    }

                    if (fieldUpdateList ==null ) {
                        fieldUpdateList = new ArrayList<>();
                        fieldUpdateMap = new HashMap<>();
                        map = new HashMap<>();
                    }
//                    fieldUpdateList.add( val -> getGetMethod(tClass , field.getName()));
                    SFunction<T , ?> sFunction = val -> getGetMethod(val.getClass() , field.getName());
                    fieldUpdateMap.put(sFunction, field.get(t) );
                    map.put(field.getName() , field.get(t));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private static Method getGetMethod(Class<?> clazz, String fieldName) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if(("get"+fieldName).equalsIgnoreCase(method.getName())){
                return method;
            }
        }
        throw new RuntimeException("Error");
    }

    private static boolean checkBaseTypeContinue(Field field) {
        return field.getType() == int.class ||
                field.getType() == long.class ;
    }

}
