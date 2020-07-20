package pres.qianmuna.rpc.server;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/20  17:58
 * @description :
 */
public class RpcServer implements Runner{
    /**
     * 注册 表
     * k 业务接口 名
     * v 实现类实例
     */
    private Map<String,Object> rejisterMap = new HashMap<>();

    /**
     * 缓存 包下 业务接口实现类
     */
    private List<String> classCache = Collections.synchronizedList(new ArrayList<>());

    /**
     * 服务 发布
     * @param basePackage Package
     */
    public void publish(String basePackage){
        getProviderClass(basePackage);

        try {
            doRegister();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布
     */
    private void doRegister() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (classCache.isEmpty())
            return;
        // get ins
        for (String s : classCache) {
            Class<?> aClass = Class.forName(s);
            // 单个接口 单个实现
            rejisterMap.put(aClass.getInterfaces()[0].getName() , aClass.newInstance());

        }

    }

    /**
     * get instance
     * @param basePackage basePackage
     */
    private void getProviderClass(String basePackage) {
        // scan data
        URL url = this.getClass().getClassLoader()
                .getResource(basePackage.replaceAll("\\.", "/"));

        if (null == url)
            return;

        //read file
        File dir = new File(url.getFile());
        // scan .class
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            // dir
            if (file.isDirectory())
                getProviderClass(basePackage + "." + file.getName());

            if (!file.getName().endsWith(".class"))
                continue;
            // CLASS NAME
            classCache.add(basePackage + "." + file.getName().replace(".class" , "").trim());
        }
    }

    @Override
    public void start(){
        
    }
}
