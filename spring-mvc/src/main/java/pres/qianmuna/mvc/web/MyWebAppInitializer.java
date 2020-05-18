package pres.qianmuna.mvc.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import pres.qianmuna.mvc.web.app.AppConfig;
import pres.qianmuna.mvc.web.root.RootConfig;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 15:26
 */
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /*
    * web 容器启动时候创建对象 调用方法初始化容器 的控制器
    *
    * */

    /**
     *
     * 得到跟容器配置类 跟spring配置的listener一 样
     *
     * 父容器
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     *
     * 得到web 容器配置类
     * 子容器
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }


    /**
     *
     * 得到前端控制器的映射信息
     * 拦截所有请求
     * /* 是拦截所有包括jsp页面
     * / 是不拦截jsp页面的
     * jsp页面是由tomcat的jsp 引擎解析的
     *
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {

        return new String[]{"/"};
    }
}
