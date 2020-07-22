package pres.qianmuna.rpc.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/22  20:57
 * @description :
 */
@Data
@Accessors( chain = true)
public class Invocation implements Serializable {

    private static final long serialVersionUID = -7177871863989004333L;

    /**
     * 接口 名
     */
    private String className;

    /**
     * 方法
     */
    private String methodName;

    /**
     * 参数类型
     */
    private Class<?>[] paramType;

    /**
     * 参数 值
     */
    private Object[] paramValues;
}
