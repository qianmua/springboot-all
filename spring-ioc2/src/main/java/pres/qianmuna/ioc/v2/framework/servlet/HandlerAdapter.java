package pres.qianmuna.ioc.v2.framework.servlet;

import pres.qianmuna.ioc.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
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

        // 参数 顺序
        // 参数 转化
        Map<String , Integer> paramIndex = new HashMap<>();
        //** 参数 转化
        Annotation[][] annotations = handler.getMethod().getParameterAnnotations();
        // 保存 参数名 和 下标
        for (int i = 0; i < annotations.length; i++) {
            for (Annotation annotation : annotations[i]) {
                if (annotation instanceof RequestParam){
                    String pName = ((RequestParam) annotation).value().trim();
                    paramIndex.put(pName , i);
                }
            }
        }
        // 匹配 形参 列表
        // 参数类型
        Class<?>[] types = handler.getMethod().getParameterTypes();
        for (int i = 0; i < types.length; i++) {
            Class<?> type = types[i];
            if (type == HttpServletRequest.class || type == HttpServletResponse.class)
                // save index
                paramIndex.put(type.getName(), i);
        }

        // 实参
        Object[] values = new Object[types.length];
        Map<String, String[]> params = req.getParameterMap();
        // 实参 赋值
        // 判断 类型
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            // 解析 并得到 参数
            String valueName = Arrays.toString(params.get(entry.getKey()))
                    .replaceAll("[\\[\\]]", "")
                    .replaceAll("\\s", "");

            if (!paramIndex.containsKey(entry.getKey()))
                continue;

            Integer index = paramIndex.get(entry.getKey());
            values[index] = caseStringValue(valueName,types[index]);
        }


        //执行
        Object invoke = handler.getMethod().invoke(handler.getController(), req, resp, values);
        // 不返回 视图
        if (invoke == null || invoke instanceof Void)
            return null;

        // 视图
        if (handler.getMethod().getReturnType() == ModelAndView.class)
            return (ModelAndView) invoke;

        // not
        return null;
    }

    /**
     * 动态 参数 转换
     * @param valueName valueName
     * @param type type
     * @return type
     */
    private Object caseStringValue(String valueName, Class<?> type) {
        if (String.class == type)
            return valueName;
        if (Integer.class == type)
            return Integer.valueOf(valueName);
        if (Double.class == type)
            return Double.valueOf(valueName);
        else
            return valueName;
    }
}
