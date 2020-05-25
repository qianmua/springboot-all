package pres.qianmuna.oauth;

import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/25
 * @time 22:57
 */
@RunWith(SpringRunner.class)
public class Test {


    @org.junit.Test
    public void text(){
        String haspw = BCrypt.hashpw("123" , BCrypt.gensalt());

        System.out.println(haspw);
    }
}
