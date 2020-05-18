package pres.qianmuna.annotation.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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

    private String name;
    private int age;
}
