package pres.qianmuna.ioc.v2.framework.servlet;

import pres.qianmuna.ioc.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/16  20:24
 * @description : HandlerAdapter
 */
public class HandlerAdapter {

    /**
     * 视图对象
     * 动态匹配参数
     * @param req req
     * @param resp resp
     * @param handler handler
     * @return ModelAndView
     */
    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handler) throws InvocationTargetException, IllegalAccessException {

        Method method = handler.getMethod();
        // 形参
        // 参数类型
        Class<?>[] types = method.getParameterTypes();

        // 参数 顺序
        // 参数 转化
        Map<String , Integer> paramIndex = new HashMap<>();
        //** 参数 转化
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

        // 实参
        Map<String, String[]> parameterMap = req.getParameterMap();
        Object[] values = new Object[types.length];



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

        Object invoke = method.invoke(handler.getController(), req, resp, values);
        // 不返回 视图
        if (invoke == null || invoke instanceof Void)
            return null;

        // 视图
        if (method.getReturnType() == ModelAndView.class)
            return (ModelAndView) invoke;

        return null;
    }
}
