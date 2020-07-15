package pres.qianmuna.ioc.v2.framework;

import pres.qianmuna.ioc.annotation.Controller;
import pres.qianmuna.ioc.annotation.RequestMapping;
import pres.qianmuna.ioc.annotation.RequestParam;
import pres.qianmuna.ioc.annotation.Service;
import pres.qianmuna.ioc.v2.framework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/14  21:47
 * @description :
 */
public class DispatcherServlet extends HttpServlet {


    private static final long serialVersionUID = -113306101643492603L;


    /**
     * context
     */
    private ApplicationContext applicationContext;


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

        // 形参
        // 参数类型
        Class<?>[] types = method.getParameterTypes();

        // 实参
        Map<String, String[]> parameterMap = req.getParameterMap();
        Object[] values = new Object[types.length];

        // 参数 顺序
        Map<String , Integer> paramIndex = new HashMap<>();

        // 优化
        Annotation[][] params = method.getParameterAnnotations();
        // 保存 参数名 和 下标
        for (int i = 0; i < params.length; i++) {
            for (Annotation annotation : params[i]) {
                if (annotation instanceof RequestParam){
                    String pName = ((RequestParam) annotation).value().trim();
                    paramIndex.put(pName , i);

                }
            }
        }

        // 实参 赋值
        // 判断 类型
        for (int i = 0; i < values.length; i++) {
            Class<?> type = types[i];
            if (type == HttpServletRequest.class)
                values[i] = req;
            else if (type == HttpServletResponse.class)
                values[i] = resp;
            else if (type == String.class){
                // 得到 参数 注解
                // 有注解?

                //优化
                // 从下标中得到 //
//                String value = paramIndex.get();
                /*if (!"".equals(value)){
                    // 解析 并得到 参数
                    String valueName = Arrays.toString(parameterMap.get(value))
                            .replaceAll("[\\[\\]]", "")
                            .replaceAll("\\s", "");
                    // 赋值
                    values[i] = valueName;
                }*/
                Annotation[][] pa = method.getParameterAnnotations();
                for (Annotation annotation : pa[i]) {
                    // 是当前 注解？
                    if (annotation instanceof RequestParam){
                        String value = ((RequestParam) annotation).value().trim();
                        if (!"".equals(value)){
                            // 解析 并得到 参数
                            String valueName = Arrays.toString(parameterMap.get(value))
                                    .replaceAll("[\\[\\]]", "")
                                    .replaceAll("\\s", "");
                            // 赋值
                            values[i] = valueName;
                        }
                    }
                }
            }
            else
                values[i] = null;

        }

        // 得到 对象
        // beanName
        // 从application 上下文
        // 执行
        method.invoke(applicationContext.getBean(method.getDeclaringClass()) , req, resp , values);

    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        applicationContext = new ApplicationContext(config.getInitParameter("contextConfigLocation"));

        //=============mvc=================
        // init HandlerMapping
        doInitHandlerMapping();

        // success
        System.out.println("spring framework init success.");
    }

    /**
     * 初始化 handler
     */
    private void doInitHandlerMapping() {
        if (this.applicationContext.getBeanDefinitionCount() == 0)
            return;

        String[] beanNames = this.applicationContext.getBeanDefinitionNames();

        for (String beanName: beanNames) {

            Object bean = this.applicationContext.getBean(beanName);

            //扫描 方法
            Class<?> aClass = bean.getClass();

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
}
