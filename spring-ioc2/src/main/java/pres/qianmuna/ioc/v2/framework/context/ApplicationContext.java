package pres.qianmuna.ioc.v2.framework.context;

import pres.qianmuna.ioc.v2.framework.beans.BeanWrapper;
import pres.qianmuna.ioc.v2.framework.beans.config.BeanDefinition;
import pres.qianmuna.ioc.v2.framework.beans.support.BeanDefinitionReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        return this.factoryBeanInstanceCache.get(beanName).getWrapperInstance();
    }

    /**
     * di 注入
     * @param beanName beanName
     * @param definition definition
     * @param wrapper wrapper
     */
    private void populateBean(String beanName, BeanDefinition definition, BeanWrapper wrapper) {

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
            //缓存 原型对象
            this.factoryBeanObjectCache.put(beanName , instance);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }


    /**
     * getBean
     * @param className
     * @return
     */
    public Object getBean(Class className){
        return getBean(className.getName());
    }
}
