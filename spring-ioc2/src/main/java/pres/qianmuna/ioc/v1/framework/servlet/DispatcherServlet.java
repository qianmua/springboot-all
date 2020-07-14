package pres.qianmuna.ioc.v1.framework.servlet;

import pres.qianmuna.ioc.v1.framework.annotation.Autowired;
import pres.qianmuna.ioc.v1.framework.annotation.Controller;
import pres.qianmuna.ioc.v1.framework.annotation.RequestMapping;
import pres.qianmuna.ioc.v1.framework.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    /**
     *  handler 容器
     */
    private Map<String , Method> handlerMapping = new HashMap<>();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req,resp);
        } catch (IOException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            resp.getWriter().write("500 exception error -> " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * 调用 具体 方法
     * @param req
     * @param resp
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {

        //得到请求路径
        String uri = req.getRequestURI();
        // 相对 路径
        String contextPath = req.getContextPath();

        // 处理 路径
        uri = ( "/" + uri).replaceAll(contextPath , "").replaceAll("/+" , "/");


        if (!this.handlerMapping.containsKey(uri))
            resp.getWriter().write("404 not found!");


        Method method = this.handlerMapping.get(uri);

        // 调用 1、obj 2、param
        // ...[]

        // 实参
        Map<String, String[]> parameterMap = req.getParameterMap();
        // 得到 对象
        // beanName
        // 从ioc 中 获取
        String simpleName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        // 执行
        method.invoke(ioc.get(simpleName) , req, resp , parameterMap.get("name")[0] , parameterMap.get("age")[0]);

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
        if (ioc.isEmpty())
            return;
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {

            //扫描 方法
            Class<?> aClass = entry.getValue().getClass();

            // 是个 controller？
            if (aClass.isAnnotationPresent(Controller.class))
                continue;

            //controller url
            //
            String baseUrl = "";
            if (aClass.isAnnotationPresent(RequestMapping.class)){
                Controller annotation = aClass.getAnnotation(Controller.class);
                baseUrl = annotation.value().trim();
            }

            // 得到 method
            for (Method method : aClass.getMethods()) {

                // 是个接口访问？
                if (!method.isAnnotationPresent(RequestMapping.class))
                    continue;

                RequestMapping mapping = method.getAnnotation(RequestMapping.class);

                // 接口 路径
                // 接口 / 修正
                String url = ("/" + baseUrl + "/" + mapping.value() ).replaceAll("/+" , "/");

                // 添加 到 handler 容器 后面调用
                handlerMapping.put(url , method );
                System.out.println(" scan url -> " + url);

            }
        }
    }

    /**
     * di
     */
    private void doAutowired() {
        // 注入
        if (ioc.isEmpty())
            return;
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {

            // 得到 所有类型字段
            // private public protected default
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            // 赋值
            for (Field field : fields) {
                // 是否有 注解
                if (!field.isAnnotationPresent(Autowired.class))
                    continue;
                Autowired annotation = field.getAnnotation(Autowired.class);
                // 得到注解值
                String value = annotation.value().trim();

                // beanName
                if ("".equals(value)){
                    value = field.getType().getName();
                }

                // private 等 是不可 直接访问的
                // 强制 级别 访问
                field.setAccessible(true);

                try {
                    // 设置 值
                    // 注入
                    // 给 字段 属性//
                    field.set(entry.getValue() , ioc.get(value));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


            }

        }
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

                //判断 是否 是个Bean
                // 有 相关 注解
                if (! (aClass.isAnnotationPresent(Controller.class)
                        || aClass.isAnnotationPresent(Service.class))){
                    continue;
                }
                // bean Name
                // 首字母小写
                String beanName = toLowerFirstCase(aClass.getSimpleName());
                // 不同包 同名？
                // 自定义beanName
                // 读取到 注解 的值
                Service service = aClass.getAnnotation(Service.class);
                if ("".equals(service.value())){
                    beanName = service.value();
                }

                // 反射到 对象
                Object instance = aClass.newInstance();

                //添加 到ioc
                ioc.put(beanName , instance);

                // 处理接口
                // 使用 实现类 去 赋值
                for (Class<?> anInterface : aClass.getInterfaces()) {
                    if (ioc.containsKey(anInterface.getName())){
                        throw new Exception("bean name \" " + anInterface.getName() + " \" is exists");
                    }
                    ioc.put(anInterface.getName() , instance);
                }



            } catch (Exception e) {
                e.printStackTrace();
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
