package pres.qianmuna.dubbo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/21
 * @time 18:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors( chain = true)
public class User implements Serializable {

    private Long uid;

}
