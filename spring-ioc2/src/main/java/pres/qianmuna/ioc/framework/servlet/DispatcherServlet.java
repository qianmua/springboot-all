package pres.qianmuna.ioc.framework.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/14  21:47
 * @description :
 */
public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = 8339850291113236937L;


    /**
     * ioc 容器
     */
    private Map<String ,Object> ioc = new HashMap<>();


    /**
     * 解析  配置文件
     */
    private Properties contextConfig = new Properties();


    /**
     * 保存 扫描到的 类名
     */
    private List<String> classNames = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doDispatch(req,resp);
    }

    /**
     * 调用 具体 方法
     * @param req
     * @param resp
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        // 读取 配置 文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        // 初始化

        // 扫描相关类
        doScanner(contextConfig.getProperty("scan-package"));

        // 实例化 ， 并且 缓存到 缓存
        doInstance();

        // di
        doAutowired();

        // init HandlerMapping
        doInitHandlerMapping();

        // success
        System.out.println("spring framework init success.");
    }

    /**
     * 初始化 handler
     */
    private void doInitHandlerMapping() {
    }

    /**
     * di
     */
    private void doAutowired() {
    }

    /**
     * 实例化 缓存到 IOC
     */
    private void doInstance() {
        // 实例化

        if (classNames.isEmpty())
            return;

        for (String name : classNames) {

            try {
                // 得到 反射Class
                Class<?> aClass = Class.forName(name);
                // 反射到 对象
                Object instance = aClass.newInstance();

                // bean Name
                // 首字母小写
                String beanName = null;

                //添加 到ioc


            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
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
                classNames.add(fileName);
            }

        }

    }

    /**
     * 读取配置文件
     * @param contextConfigLocation
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
}
