package pres.qianmuna.client.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/16
 * @time 18:50
 */
@Target( ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiServer {

    /**
     * 路由接口
     * @return
     */
    String value() default "";

}
