package pres.qianmuna.shiro2.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/16  22:15
 * @description :
 */
@Data
@NoArgsConstructor
@Accessors( chain = true)
public class CommonGlobalsJSON<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonGlobalsJSON(Integer code , String message){
        this(code , message , null);
    }

    public CommonGlobalsJSON(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
