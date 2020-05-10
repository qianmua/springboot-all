package pres.qianmuna.exception.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/10
 * @time 13:48
 */
@RestControllerAdvice
public class GlobalsException {

    @ExceptionHandler( Exception.class)
    Map<String , Object> handlerException(Exception e , HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>();

        map.put("code" , 100);
        map.put("msg" , e.getMessage());
        map.put("url" , request.getRequestURL());

        //处理状态码
        return map;
    }

}
