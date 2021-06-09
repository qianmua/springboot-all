package pers.jc.flow.engine.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/6/9  10:53
 */
@Component
public class BeanService implements ApplicationContextAware {

    protected static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanService.context = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> T getBeanByName(String name) throws BeansException {
        Object bean = context.getBean(name);
        return (T) bean;
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> T getBeanByType(Class<?> clazz) throws Exception {
        return (T) getApplicationContext().getBean(clazz);
//        throw new RuntimeException("Not found bean [" + clazz.getName() +"] by class. ");
    }

}
