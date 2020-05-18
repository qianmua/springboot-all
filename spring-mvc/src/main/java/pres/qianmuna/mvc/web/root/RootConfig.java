package pres.qianmuna.mvc.web.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 15:30
 */

/**
 * 扫描内容
 * 排除controller 等
 *
 * 形成父子关系
 *
 */
@ComponentScan(value = "pres.qianmuna.mvc.web" ,excludeFilters = {
        @ComponentScan.Filter( type = FilterType.ANNOTATION , classes = {Controller.class})
})
public class RootConfig {
}
