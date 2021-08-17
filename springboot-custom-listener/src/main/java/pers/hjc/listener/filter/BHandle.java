package pers.hjc.listener.filter;

import org.springframework.stereotype.Component;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/8/17  11:22
 */
@Component
public class BHandle implements Handle{
    @Override
    public boolean handle(String task) {
        if ("B".equals(task)){
            System.out.println("B");
            return true;
        }
        return false;
    }
}
