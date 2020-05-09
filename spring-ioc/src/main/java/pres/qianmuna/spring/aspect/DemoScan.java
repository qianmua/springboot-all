package pres.qianmuna.spring.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/9
 * @time 12:26
 */
@Target(ElementType.TYPE)
public @interface DemoScan {
    String value() default "";
}
