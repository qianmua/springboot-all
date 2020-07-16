package pres.qianmuna.ioc.v2.framework.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/16  20:24
 * @description : HandlerMapping
 */
public class HandlerMapping {

    /**
     * api
     */
    private Pattern url;

    /**
     * controller Bean
     */
    private Object controller;


    /**
     * use method
     */
    private Method method;

    public HandlerMapping(Pattern url, Object controller, Method method) {
        this.url = url;
        this.controller = controller;
        this.method = method;
    }

    public Pattern getUrl() {
        return url;
    }

    public void setUrl(Pattern url) {
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
}
