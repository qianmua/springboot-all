package pres.qianmuna.mp.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/2  20:22
 * @description :
 */
@Data
@Accessors( chain = true)
public class R {

    private Integer code;

    private String message;

    private Map<String , Object> data = new HashMap<>();

    private R(){}


    public static R ok(){
        return new R()
                .setCode(200)
                .setMessage("SUCCESS")
                ;
    }

    public static R fail(){
        return new R()
                .setCode(500)
                .setMessage("FAIL")
                ;
    }


    public R data(String key ,  Object value){
        this.data.put(key , value);
        return this;
    }
}
