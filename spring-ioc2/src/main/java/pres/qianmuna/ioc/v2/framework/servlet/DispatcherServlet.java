package pres.qianmuna.ioc.v2.framework.servlet;

import pres.qianmuna.ioc.annotation.Controller;
import pres.qianmuna.ioc.annotation.RequestMapping;
import pres.qianmuna.ioc.annotation.RequestParam;
import pres.qianmuna.ioc.v2.framework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/14  21:47
 * @description : DispatcherServlet
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
    @Deprecated
    private Map<String , HandlerMapping> handlerMappings = new HashMap<>();

    /**
     * handlerMapping 包含 url
     */
    private List<HandlerMapping> handlerMappingList = new ArrayList<>();

    /**
     * adapter 容器
     */
    private Map<HandlerMapping , HandlerAdapter> handlerAdapters = new HashMap<>();

    /**
     * 视图 容器
     */
    private List<ViewResolver> viewResolvers = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            doDispatch(req,resp);
        } catch (IOException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
//            resp.getWriter().write("500 exception error -> " + Arrays.toString(e.getStackTrace()));

            Map<String , Object> model = new HashMap<>();
            model.put("detail", "server error 500 !");
            model.put("stackTrace", Arrays.toString(e.getStackTrace()));
            processDispatchResult(req,resp , new ModelAndView("500" , model));
        }
    }

    /**
     * 调用 具体 方法
     * @param req req
     * @param resp  resp
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        // get handler
        HandlerMapping handler = getHandler(req);

        if (handler == null){
            processDispatchResult(req,resp,new ModelAndView("404"));
            return;
        }

        // get HandlerAdapter
        HandlerAdapter adapter = getAdapter(handler);

        ModelAndView modelAndView = Objects.requireNonNull(adapter).handle(req,resp,handler);

        // 选择 viewResolver
        processDispatchResult(req,resp,modelAndView);


    }

    /**
     * 得到 参数 适配
     * @param handler handler
     * @return adapter
     */
    private HandlerAdapter getAdapter(HandlerMapping handler) {
        if (this.handlerAdapters.isEmpty())
            return null;
        return this.handlerAdapters.get(handler);
    }

    /**
     * 统一 结果 处理
     * @param req req
     * @param resp res
     * @param modelAndView view
     */
    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, ModelAndView modelAndView) throws IOException {

        if (null == modelAndView )
            return;
        if (this.viewResolvers.isEmpty())
            return;
        // 查找 视图
        for (ViewResolver resolver : this.viewResolvers) {
            View view = resolver.resolverViewName(modelAndView.getViewName());
            // 渲染
            view.render(modelAndView.getModel() , req,resp);
        }
    }

    /**
     * get HandlerMapping
     * @param req url
     * @return handler
     */
    private HandlerMapping getHandler(HttpServletRequest req) {
        //get url
        String uri = req.getRequestURI();
        String path = req.getContextPath();
        uri = ( "/" + uri).replaceAll(path , "").replaceAll("/+" , "/");

        // 匹配
        for (HandlerMapping mapping : handlerMappingList) {
            // 得到 匹配式
            Matcher matcher = mapping.getUrl().matcher(uri);
            if (!matcher.matches())
                continue;

            return mapping;
        }
        // null 404
        return null;
    }


    /**
     * init context
     * @param config web.xml config
     * @throws ServletException err
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化 spring
        applicationContext = new ApplicationContext(config.getInitParameter("contextConfigLocation"));

        // 初始化 组件
        initStrategies(applicationContext);

        // success
        System.out.println("spring framework init success.");
    }

    /**
     * 初始化 组件
     * @param applicationContext spring
     */
    private void initStrategies(ApplicationContext applicationContext) {
        // file upload
        initMultipartResolver(applicationContext);
        // lang
        initLocaleResolver(applicationContext);
        // 主题模板
        initThemeResolver(applicationContext);
        // handler mapping
        initHandlerMappings(applicationContext);
        // adapter适配
        initHandlerAdapters(applicationContext);
        // 异常拦截
        initHandlerExceptionResolvers(applicationContext);
        // 视图预处理 // 视图 提取
        initRequestToViewNameTranslator(applicationContext);
        // 视图转换 初始化
        initViewResolvers(applicationContext);
        // flashMap 管理
        initFlashMapManager(applicationContext);
    }

    /**
     * 主题模板
     * @param applicationContext spring
     */
    private void initThemeResolver(ApplicationContext applicationContext) {
    }

    /**
     * flashMap 管理
     * 参数缓存
     * 页面跳转 防止 参数丢失
     * 作用域 对象
     * @param applicationContext spring
     */
    private void initFlashMapManager(ApplicationContext applicationContext) {
    }

    /**
     * 视图转换 初始化
     * 模板引擎
     * @param applicationContext spring
     */
    private void initViewResolvers(ApplicationContext applicationContext) {
        // a page -> a resolver

        // 读取 配置 路径
        String templateRoot = applicationContext.getConfig().getProperty("template-root");
        // 路径下fileName
        String classPathFileName = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File templateRootFile = new File(classPathFileName);
        for (File file : Objects.requireNonNull(templateRootFile.listFiles())) {
            //a file -> a resolver
            this.viewResolvers.add(new ViewResolver(templateRoot));

        }

    }

    /**
     * 视图预处理
     * 视图 提取
     * request 中
     * @param applicationContext spring
     */
    private void initRequestToViewNameTranslator(ApplicationContext applicationContext) {
    }

    /**
     * 异常拦截
     * @param applicationContext spring
     */
    private void initHandlerExceptionResolvers(ApplicationContext applicationContext) {
    }

    /**
     * handlerAdapter
     * 动态参数 适配
     * @param applicationContext spring
     */
    private void initHandlerAdapters(ApplicationContext applicationContext) {
        // a method - > a adapter
        for (HandlerMapping handlerMapping : handlerMappingList) {
            this.handlerAdapters.put(handlerMapping , new HandlerAdapter());
        }

    }

    /**
     * handlerMapping
     * url 映射
     * @param applicationContext spring
     */
    private void initHandlerMappings(ApplicationContext applicationContext) {
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
                String regex = ("/" + baseUrl + "/" + mapping.value() )
                        .replaceAll("\\*" , ".*")
                        .replaceAll("/+" , "/");

                // 正则 表示
                // 匹配
                Pattern pattern = Pattern.compile(regex);


                // 添加 到 handler 容器 后面调用
                handlerMappingList.add(new HandlerMapping(pattern , bean  ,method) );
                System.out.println(" scan url -> " + pattern);

            }
        }
    }

    /**
     * 初始化 本地语言 环境
     * @param applicationContext spring
     */
    private void initLocaleResolver(ApplicationContext applicationContext) {
    }

    /**
     * 多文件 上传
     * @param applicationContext spring
     */
    private void initMultipartResolver(ApplicationContext applicationContext) {
    }

}
