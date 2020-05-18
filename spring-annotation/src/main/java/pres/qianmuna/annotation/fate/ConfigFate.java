package pres.qianmuna.annotation.fate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pres.qianmuna.annotation.bean3.Car;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 12:11
 */
@Configuration
public class ConfigFate {


    /**
     *
     * 自定义初始化 销毁
     *
     * 单实例bean 容器管理销毁舒适化
     *
     * 多实例 容器不会管理销毁
     *
     * @return
     */
    @Bean( initMethod = "init" , destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }
}
