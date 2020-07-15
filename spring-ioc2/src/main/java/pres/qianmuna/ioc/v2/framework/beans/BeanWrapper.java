package pres.qianmuna.ioc.v2.framework.beans;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/15  18:28
 * @description : cache -
 */
public class BeanWrapper {

    // 实例谁？
    private Object wrapperInstance;
    // 原型对象
    private Class<?> wrapperClass;

    /**
     * 封装
     * @param bean
     */
    public BeanWrapper(Object bean) {
        this.wrapperInstance = bean;
        this.wrapperClass = wrapperInstance.getClass();
    }

    /**
     * 封装//
     * @return
     */
    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    /**
     * 原型
     * @return
     */
    public Class<?> getWrapperClass() {
        return wrapperClass;
    }
}
