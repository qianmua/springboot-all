package pres.qianmuna.ioc.v2.framework.aop.support;

import pres.qianmuna.ioc.v2.framework.aop.aspect.Advice;
import pres.qianmuna.ioc.v2.framework.aop.config.AopConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/17  15:38
 * @description : AdvicedSupport
 */
public class AdvicedSupport {

    private Class<?> targetClass;

    private Object target;

    private AopConfig config;

    private Pattern pointCutClassPattern;

    private Map<Method , Map<String , Advice>> methodCache;

    /**
     * init config
     * @param config config
     */
    public AdvicedSupport(AopConfig config) {
        this.config = config;
    }

    /**
     * 目标类
     * @param aClass aClass
     */
    public void setTargetClass(Class<?> aClass) {
        this.targetClass = aClass;
        parse();
    }

    /**
     * 解析 是否 代理 前置
     */
    private void parse() {

        // 替换为 正则 识别 表达式
        String pointCut = this.config.getPointCut()
                .replaceAll("\\." , "\\\\.")
                .replaceAll("\\\\.\\*" , ".*")
                .replaceAll("\\(" , "\\\\(")
                .replaceAll("\\)" , "\\\\)");


        String pointCutForClassRegex = pointCut.substring(0 , pointCut.lastIndexOf("\\(") -4);

        // 匹配 类
        pointCutClassPattern = Pattern.compile(pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ") +1));

        // 匹配 方法
        Pattern pointCutPattern = Pattern.compile(pointCut);

        //缓存 切面 类 中 方法
        methodCache = new HashMap<>();
        Map<String , Method> stringMethod = new HashMap<>();
        Class<?> aspectClass;
        try {
            aspectClass = Class.forName(this.config.getAspectClass());

            for (Method method : aspectClass.getMethods()) {
                stringMethod.put(method.getName() , method);
            }

            // 扫描 目标类 中 方法
            for (Method method : this.getTargetClass().getMethods()) {
                // 全类名
                String methodName = method.toString();
                if (methodName.contains("throws")){
                    methodName = methodName.substring(0 , methodName.lastIndexOf("throws")).trim();
                }
                // 匹配
                // 器
                Matcher matcher = pointCutPattern.matcher(methodName);

                if (matcher.matches()){
                    // 建立 关联关系
                    Map<String,  Advice> adviceMap = new HashMap<>();

                    //前置
                    if ( !(null == this.config.getAspectBefore() || "".equals(this.config.getAspectBefore()) ) )
                        adviceMap.put("before" , new Advice(aspectClass.newInstance() , stringMethod.get(this.config.getAspectBefore())));

                    // 后置
                    if ( !(null == this.config.getAspectAfter() || "".equals(this.config.getAspectAfter()) ) )
                        adviceMap.put("after" , new Advice(aspectClass.newInstance() , stringMethod.get(this.config.getAspectAfter())));
                    // 异常
                    if ( !(null == this.config.getAspectAfterThrow() || "".equals(this.config.getAspectAfterThrow()) ) ){
                        Advice advice = new Advice(aspectClass.newInstance() , stringMethod.get(this.config.getAspectAfterThrow()));
                        advice.setThrowName(this.config.getAspectAfterThrowingName());
                        adviceMap.put("after-throwing" , advice);
                    }

                    // 放入 容器
                    methodCache.put(method , adviceMap);

                }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 触发类
     * @param instance instance
     */
    public void setTarget(Object instance) {
        this.target = instance;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }

    /**
     * 匹配
     * 需要代理？
     * @return 代理
     */
    public boolean pointCutMatch() {
        return pointCutClassPattern.matcher(this.targetClass.getName()).matches();
    }

    /**
     * 织入
     * 得到 代理类 对应的通知 回调
     * @param method method
     * @param targetClass targetClass
     * @return Map
     */
    public Map<String, Advice> getAdvice(Method method, Class<?> targetClass) throws NoSuchMethodException {
        Map<String , Advice> cache = methodCache.get(method);

        // 可能是 代理 的 方法
        // 需要 原生的
        // 转换
        if (null == cache){
            Method method1 = targetClass.getMethod(method.getName(), method.getParameterTypes());
            cache = methodCache.get(method1);
            this.methodCache.put(method1 , cache);
        }

        return cache;
    }
}
