package pres.qianmuna.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 11:21
 */
@Configuration
@ComponentScan(value = "pres.qianmuna.annotation.bean", includeFilters = {
        @ComponentScan.Filter( type = FilterType.ANNOTATION , classes = {Controller.class})

},useDefaultFilters = false)
/**
 * includeFilters 导入规则
 * Filter过滤 只扫描Controller
 * excludeFilters 排除那些
 *
 * FilterType 注解 ， 给定样式 ，aspectJ表达式 ， 正则 ， 自定义
 */
public class ScanConfig {

    /*
    * 包扫描 注册
    * */
}
