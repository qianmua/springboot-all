package pres.qianmuna.ioc.v2.framework.beans.support;

import pres.qianmuna.ioc.v2.framework.beans.config.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/15  18:30
 * @description : tools
 */
public class BeanDefinitionReader {

    /**
     * 解析  配置文件
     */
    private Properties contextConfig = new Properties();

    /**
     * 保存 扫描到的 类名
     */
    private List<String> registerBeanClasses = new ArrayList<>();



    /**
     * load config
     * @param configLocations configs
     */
    public BeanDefinitionReader(String[] configLocations) {
        // 加载 读取 配置
        doLoadConfig(configLocations[0]);

        // 解析
        // 扫描
        doScanner(contextConfig.getProperty("scan-package"));
    }

    /**
     * newBean
     *
     * @return ioc
     */
    public List<BeanDefinition> doLoadBeanDefinitions() {
        ArrayList<BeanDefinition> result = new ArrayList<>();
        //  ->
        //添加到 ioc
        try {
            for (String className : registerBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface())
                    continue;
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()) ,beanClass.getName()));

                // 处理接口
                for (Class<?> anInterface : beanClass.getInterfaces()) {
                    result.add(doCreateBeanDefinition(anInterface.getName() ,beanClass.getName()));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * create bean
     *  包装
     * @return bean
     */
    private BeanDefinition doCreateBeanDefinition(String factoryBeanName , String beanClassName) {
        BeanDefinition definition = new BeanDefinition();
        definition.setFactoryBeanName(factoryBeanName);
        definition.setBeanClassName(beanClassName);
        return definition;
    }

    /**
     * 扫描相关类
     * @param property
     */
    private void doScanner(String property) {

        // 修改 包路径 到 文件路径
        URL url = this.getClass().getClassLoader().getResource("/" + property.replaceAll("\\.", "/"));
        // 封装 url
        // 读取  class file
        File file = new File(Objects.requireNonNull(url).getFile());
        // 遍历 下 所有 class 文件
        for (File listFile : Objects.requireNonNull(file.listFiles())) {

            if (listFile.isDirectory()){
                // 遍历子目录
                doScanner(property + "." + listFile.getName());
            }else {
                // 只读取.class 文件
                if (!listFile.getName().endsWith(".class")){
                    continue;
                }
                // 拼接 全类名
                // 反射得到 对象
                // 得到 类名 啦~
                String fileName = property + "." + listFile.getName().replace(".class", "");
                registerBeanClasses.add(fileName);
            }

        }

    }

    /**
     * 读取配置文件
     * @param contextConfigLocation config
     */
    private void doLoadConfig(String contextConfigLocation) {

        // classpath 加载 到 配置 文件
        // properties
        // 读取到数据流
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);

        try {
            contextConfig.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != stream){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 小写首字母
     * @param simpleName
     * @return
     */
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        // 小写
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
