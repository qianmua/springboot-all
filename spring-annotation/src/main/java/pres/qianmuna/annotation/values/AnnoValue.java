package pres.qianmuna.annotation.values;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import pres.qianmuna.annotation.bean.Persoin;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 13:10
 */
@Configuration
@PropertySource( value = {"classpath:person.properties"})
public class AnnoValue {

    /*
    * @Value
    * */


    // spel #{}
    // ${} 取出配置

    @Bean
    public Persoin persoin(){
        return new Persoin();
    }
}
