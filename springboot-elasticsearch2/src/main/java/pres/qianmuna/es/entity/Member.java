package pres.qianmuna.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/12  18:58
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors( chain = true)
public class Member {
    private String id;
    private String name;
    private String address;
}
