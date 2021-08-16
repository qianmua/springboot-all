package pres.qianmuna.mp.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.property.PropertyNamer;
import pres.qianmuna.mp.entity.Users2;
import pres.qianmuna.mp.entity.Users3;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/8/16  10:33
 */
public class UpdateWrapperUtil {

    public static void main(String[] args) {
        Users2 users2 = new Users2();
        users2.setUid("1234");
        users2.setAddress("pu dong xin qu");
        users2.setName("Jin");

        Users3 users3 = new Users3();
        users3.setUid("1234");
        users3.setAddress("pu dong xin qu");
        users3.setName("Jin");
        users3.setDddd("JinX");
        UpdateWrapper<Users3> updateWrapper = Wrappers.update(users3);
        LambdaUpdateWrapper<Users3> lambdaUpdate = Wrappers.lambdaUpdate(Users3.class);

        /*Map<SFunction<Users3, ?>, Object> wrapper = new UpdateWrapperUtil().getLambdaUpdateWrapper(users3 , columnsMap);
        if (!wrapper.isEmpty()) {
            wrapper.forEach( (k , v) -> System.out.println(v));
            wrapper.forEach(lambdaUpdate::set);
            wrapper.forEach( (k , v) -> System.out.println(v));

        }*/

    }

    /**
     * k 类字段名 ， v lambda 包装的 可捕获的 method
     * @param <T>
     */
    public static class ColumnsMap<T> {
        // 初始大小
        private Integer DEFAULT_CAP = 8;

        private Map<Supplier<String>, SFunction<T , ?>> COLUMNS = new HashMap<>(DEFAULT_CAP);

        public ColumnsMap() { }

        public ColumnsMap(Integer initCap){
            this.DEFAULT_CAP = initCap;
        }

        public void put(SFunction<T , ?> sFunction) {
            COLUMNS.put( () -> getColumn(LambdaUtils.resolve(sFunction)), sFunction);
        }

        public SFunction<T , ?> get(String fieldName){
            for (Supplier<String> supplier : COLUMNS.keySet()) {
                if (supplier.get().equals(fieldName)) {
                    return COLUMNS.get(supplier);
                }
            }
            return null;
        }

        private String getColumn(SerializedLambda lambda) throws MybatisPlusException {
            String fieldName = PropertyNamer.methodToProperty(lambda.getImplMethodName());
            Class<?> aClass = lambda.getInstantiatedType();
            Map<String, ColumnCache> columnMap = LambdaUtils.getColumnMap(aClass);
            ColumnCache columnCache = columnMap.get(LambdaUtils.formatKey(fieldName));
            return columnCache.getColumn();
        }
    }


    public static <T> Map<SFunction<T , ?> , Object> getLambdaUpdateWrapper(T t , ColumnsMap<T> columnsMap) {
        Class<?> tClass = t.getClass();
        Map<SFunction<T , ?> , Object> fieldUpdateMap = new HashMap<>();
        collectColumns(t ,tClass , fieldUpdateMap , columnsMap);
        return fieldUpdateMap;
    }

    private static <T> void collectColumns(T t ,Class<?> tClass,
                                           Map<SFunction<T, ?>, Object> fieldUpdateMap ,
                                           ColumnsMap<T> columnsMap) {

        if (tClass != null){
            for (Field field : tClass.getDeclaredFields()) {
                // 内部类,如果是内部类需要去除掉外部类的字段引用
                boolean isMemberClass = tClass.isMemberClass();
                // set 指定字段集
                field.setAccessible(true);
                try {
                    //  这里 判断对基本类型无效
                    // 只取到 不为空的 字段
                    if (field.get(t) != null && StringUtils.isNotBlank(field.get(t).toString())) {
                        // 判断是内部类
                        if (isMemberClass){
                            // 过滤掉 final 修饰字段(内部类引用是final的)
                            // 特殊字段(final) 这里不做处理
                            if (Modifier.isFinal(field.getModifiers())) continue;
                        }
                        if (checkBaseTypeContinue(field)) continue ;

                        // 去掉排除的字段 TableField(exist = false)
                        TableField annotation = field.getAnnotation(TableField.class);
                        if (Objects.nonNull(annotation)){
                            if (!annotation.exist()) continue;
                        }
//                    SFunction<T , ?> sFunction = val -> getGetMethod(val.getClass() , field.getName());
                        fieldUpdateMap.put(columnsMap.get(field.getName()), field.get(t) );
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }else {
            return;
        }
        Class<?> superclass = tClass.getSuperclass();
        collectColumns(t ,superclass ,fieldUpdateMap , columnsMap);
    }


    /*public <T> Map<String , Object> getUpdateWrapper(T t) {
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
    }*/

   /* private static Method getGetMethod(Class<?> clazz, String fieldName) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if(("get"+fieldName).equalsIgnoreCase(method.getName())){
                return method;
            }
        }
        throw new RuntimeException("Error");
    }*/

    private static boolean checkBaseTypeContinue(Field field) {
        return field.getType() == int.class ||
                field.getType() == long.class ;
    }

}
