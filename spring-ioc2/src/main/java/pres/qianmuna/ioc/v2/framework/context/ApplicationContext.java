package pres.qianmuna.ioc.v2.framework.context;

import pres.qianmuna.ioc.annotation.Autowired;
import pres.qianmuna.ioc.annotation.Controller;
import pres.qianmuna.ioc.annotation.Service;
import pres.qianmuna.ioc.v2.framework.aop.JdkDynamicAopProxy;
import pres.qianmuna.ioc.v2.framework.aop.config.AopConfig;
import pres.qianmuna.ioc.v2.framework.aop.support.AdvicedSupport;
import pres.qianmuna.ioc.v2.framework.beans.BeanWrapper;
import pres.qianmuna.ioc.v2.framework.beans.config.BeanDefinition;
import pres.qianmuna.ioc.v2.framework.beans.support.BeanDefinitionReader;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/15  18:27
 * @description : applicationContext
 */
public class ApplicationContext {

    /**
     * 配置 信息
     */
    private String[] configLocations;

    /**
     * reader config
     */
    private BeanDefinitionReader reader;


    /**
     * bean config
     * bean 使用
     * ioc
     * 原型Bean
     */
    private Map<String ,BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 真正的 ioc
     */
    private Map<String , BeanWrapper> factoryBeanInstanceCache = new HashMap<>();


    /**
     * 原生 对象
     */
    private Map<String , Object> factoryBeanObjectCache = new HashMap<>();

    /**
     * init context
     * @param configLocations
     */
    public ApplicationContext(String ... configLocations) {
        this.configLocations = configLocations;

        try {
            // read config
            // 封装为 BeanDefinition
            // 干活的
            reader = new BeanDefinitionReader(this.configLocations);

            List<BeanDefinition> beanDefinitions = reader.doLoadBeanDefinitions();

            // bean注册到  maps
            // key beanName
            // value beanConfig
            doRegisterBeanDefinition(beanDefinitions);

            // 实例化 // -> GetBean
            //
            doCreateBean();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * create
     *  完成创建 动作
     *  di
     */
    private void doCreateBean() {
        for (Map.Entry<String, BeanDefinition> entry : this.beanDefinitionMap.entrySet()) {

            String beanName = entry.getKey();

            // 真正 完成 create
            // di
            getBean(beanName);
        }
    }

    /**
     * 注册
     * ioc Map
     * @param beanDefinitions 容器
     */
    private void doRegisterBeanDefinition(List<BeanDefinition> beanDefinitions) throws Exception {
        for (BeanDefinition definition : beanDefinitions) {
            if (this.beanDefinitionMap.containsKey(definition.getFactoryBeanName())){
                throw new Exception("The beanName ->" + definition.getFactoryBeanName() + " is exists!");
            }
            // add 容器
            this.beanDefinitionMap.put( definition.getFactoryBeanName(), definition);
            this.beanDefinitionMap.put( definition.getBeanClassName(), definition);
        }
    }

    /**
     * getBean
     * @param beanName
     * @return
     */
    public Object getBean(String beanName){

        // 得到 beanName 对应的 配置信息 beanDefinition
        BeanDefinition definition = this.beanDefinitionMap.get(beanName);
        // 实例化
        Object bean = instanceBean(beanName, definition);


        // 封装为 beanWrapper
        BeanWrapper wrapper = new BeanWrapper(bean);

        //将 wrapper 放入 真正 的 ioc 容器
        factoryBeanInstanceCache.put(beanName , wrapper);

        // di
        populateBean(beanName,definition , wrapper);

        // 从 ioc 中娶到
        return this.factoryBeanInstanceCache.get(beanName).getWrapperInstance();
    }

    /**
     * di 注入
     * @param beanName beanName
     * @param definition definition
     * @param wrapper wrapper
     */
    private void populateBean(String beanName, BeanDefinition definition, BeanWrapper wrapper) {

        Object instance = wrapper.getWrapperInstance();

        Class<?> aClass = wrapper.getWrapperClass();

        // 注解？
        if ( ! (aClass.isAnnotationPresent(Controller.class) || aClass.isAnnotationPresent(Service.class)))
            return;

        // 赋值
        for (Field field : aClass.getFields()) {
            // 是否有 注解
            if (!field.isAnnotationPresent(Autowired.class))
                continue;
            Autowired annotation = field.getAnnotation(Autowired.class);
            // 得到注解值
            String autowiredBeanName = annotation.value().trim();

            // beanName
            if ("".equals(autowiredBeanName)){
                autowiredBeanName = field.getType().getName();
            }
            // private 等 是不可 直接访问的
            // 强制 级别 访问
            field.setAccessible(true);
            try {
                // 设置 值
                // 注入
                // 给 字段 属性//
                if (this.factoryBeanInstanceCache.get(beanName) == null)
                    continue;
                field.set(instance , this.factoryBeanInstanceCache.get(autowiredBeanName).getWrapperInstance());

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * 实例化 对象
     * @param beanName beanName
     * @param definition definition
     * @return new
     */
    private Object instanceBean(String beanName, BeanDefinition definition) {

        String className = definition.getBeanClassName();
        Object instance = null;
        // 反射回来
        try {
            Class<?> aClass = Class.forName(className);
            instance = aClass.newInstance();

            // aop 代理
            // 入口
            // aop 接入
            AdvicedSupport config = instanceAopConfig(definition);
            config.setTargetClass(aClass);
            config.setTarget(instance);

            // 代理
            Objects.requireNonNull(config).setTargetClass(aClass);
            config.setTarget(instance);
            //匹配
            if (config.pointCutMatch()){
                // 覆盖 实例
                // 代理 实例
                instance = new JdkDynamicAopProxy(config).getProxy();
            }


            //缓存 原型对象
            this.factoryBeanObjectCache.put(beanName , instance);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * aop 接入
     * @param definition definition
     * @return AdvicedSupport
     */
    private AdvicedSupport instanceAopConfig(BeanDefinition definition) {
        AopConfig aopConfig = new AopConfig();

        // 解析
        aopConfig.setPointCut(this.reader.getConfig().getProperty("point-cut"));
        aopConfig.setAspectClass(this.reader.getConfig().getProperty("aspect-class"));
        aopConfig.setAspectBefore(this.reader.getConfig().getProperty("aspect-before"));
        aopConfig.setAspectAfter(this.reader.getConfig().getProperty("aspect-after"));
        aopConfig.setAspectAfterThrow(this.reader.getConfig().getProperty("aspect-after-throw"));
        aopConfig.setAspectAfterThrowingName(this.reader.getConfig().getProperty("aspect-after-throwing-name"));

        return new AdvicedSupport(aopConfig);
    }


    /**
     * getBean
     * @param className
     * @return
     */
    public Object getBean(Class className){
        return getBean(className.getName());
    }

    /**
     * 容器大小
     * @return size
     */
    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    /**
     * getBeanNames
     * @return arr
     */
    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.getBeanDefinitionCount()]);
    }

    /**
     * 返回配置信息
     * @return Properties
     */
    public Properties getConfig() {
        return this.reader.getConfig();
    }
}
