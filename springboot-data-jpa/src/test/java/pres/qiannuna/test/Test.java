package pres.qiannuna.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pres.qianmuna.jpa.mapper.UserMapper;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/7
 * @time 13:20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @Autowired
    private UserMapper userMapper;

    public void testLoad(){
        System.out.println(userMapper.findAll());
    }
}
