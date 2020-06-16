package pres.qianmuna.shiro2.common;

import lombok.Data;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/16  22:15
 * @description :
 */
@Data
public class CommonGlobalsJSON<T> {
    private Integer code;
    private String message;
    private T data;
}
