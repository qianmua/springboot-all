package pres.qianmuna.ioc.v2.framework.aop.aspect;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/17  15:39
 * @description : Advice
 */
@Data
public class Advice {

    /**
     * 切面类
     */
    private Object aspect;
    /**
     * 切面方法
     */
    private Method adviceMethod;
    /**
     * 异常名
     */
    private String throwName;

    public Advice(Object aspect, Method adviceMethod, String throwName) {
        this.aspect = aspect;
        this.adviceMethod = adviceMethod;
        this.throwName = throwName;
    }

    public Advice(Object aspect, Method adviceMethod) {
        this.aspect = aspect;
        this.adviceMethod = adviceMethod;
    }
}
