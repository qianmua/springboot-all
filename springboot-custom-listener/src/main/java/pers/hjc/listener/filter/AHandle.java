package pers.hjc.listener.filter;

import org.springframework.stereotype.Component;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/8/17  11:20
 */
@Component
public class AHandle implements Handle{
    @Override
    public boolean handle(String task) {
            if ("A".equals(task)){
            System.out.println("A");
            return true;
        }
        return false;
    }
}
