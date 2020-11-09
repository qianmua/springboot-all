package pres.qm.shiro.advite;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/11/9  15:46
 * @description :
 */
@RestControllerAdvice
@Slf4j
public class AvditerController {

    @ExceptionHandler(AuthorizationException.class)
    public String handle(Throwable e){
        log.error("err {}" , e.getMessage() );
        return "403";
    }
}
