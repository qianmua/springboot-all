package pres.qianmuna.mp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pres.qianmuna.mp.api.R;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/2  20:52
 * @description :
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class DataException {

    @ExceptionHandler( value = Exception.class)
    public R error(Exception e){
        e.printStackTrace();
        return R.fail()
                .setMessage(e.getMessage());
    }

    @ExceptionHandler( value = RuntimeException.class)
    public R error(RuntimeException e){
        e.printStackTrace();
        return R.fail()
                .setMessage(e.getMessage());
    }

    @ExceptionHandler(value = QmException.class)
    public R data(QmException q){
        log.error(q.getMessage());
        System.out.println("1");
        return R.fail()
                .setCode(Integer.valueOf(q.getCode()))
                .setMessage(q.getMessage());
    }
}
