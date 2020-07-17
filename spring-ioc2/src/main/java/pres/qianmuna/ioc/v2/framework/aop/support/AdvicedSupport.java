package pres.qianmuna.ioc.v2.framework.aop.support;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/17  15:38
 * @description : AdvicedSupport
 */
public class AdvicedSupport {

    /**
     * 目标类
     * @param aClass
     */
    public void setTargetClass(Class<?> aClass) {
    }

    /**
     * 触发类
     * @param instance
     */
    public void setTarget(Object instance) {
    }

    /**
     * 匹配
     * @return 代理
     */
    public boolean pointCutMatch() {

        return false;
    }
}
