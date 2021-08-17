package pers.hjc.listener.filter;

import org.springframework.stereotype.Component;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/8/17  11:22
 */
@Component
public class CHandle implements Handle{

    public static final String credential = "C";
    @Override
    public boolean handle(String task) {
        if ("C".equals(task)){
            System.out.println("C");
            return true;
        }
        return false;
    }
}
