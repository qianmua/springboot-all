package pres.qianmuna.ioc.v2.framework.aop.config;

import lombok.Data;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/17  15:39
 * @description : AopConfig
 */
@Data
public class AopConfig {

    /**
     * 切点
     */
    private String pointCut;
    /**
     * 切类
     */
    private String aspectClass;
    /**
     * 前置
     */
    private String aspectBefore;
    /**
     * 后置
     */
    private String aspectAfter;
    /**
     * 异常
     */
    private String aspectAfterThrow;
    /**
     * 异常名
     */
    private String aspectAfterThrowingName;
}
