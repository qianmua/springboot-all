package pres.qianmuna.annotation.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/18
 * @time 11:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors( chain = true)
public class Persoin {

    @Value("sasad")
    private String name;
    // spel #{}
    // ${} 取出配置
    @Value("#{20-10}")
    private int age;
}
