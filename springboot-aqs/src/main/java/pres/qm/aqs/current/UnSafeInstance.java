package pres.qm.aqs.current;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/18  15:32
 * @description :
 */
class UnSafeInstance {

    static Unsafe refelectGetUnsafe(){
        try {
            // 反射
            // unsafe
            Field unsafe = Unsafe.class.getDeclaredField("theUnsafe");
            // 可访问
            unsafe.setAccessible(true);
            // return
            return (Unsafe) unsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
