package pres.qianmuna.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/26  14:03
 * @description :
 */
@Aspect
@Component
public class Aop1 {

    @Around("execution(* pres.qianmuna.spring.service.Service1.test())")
    public Object invoke(ProceedingJoinPoint joinPoint){
        System.out.println("1");
        long l = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long l2 = System.currentTimeMillis();
        System.out.println("2");
        System.out.println("times -> " + (l2 - l ) + "/ms" );

        return null;
    }
}
