package pres.qianmuna.comm.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/9  18:27
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors( chain = true)
public class Commit1 implements Serializable {
    private static final long serialVersionUID = 3813191642418950935L;

    private  Long cid;
    private  Long uid;
    private  String commValue;

}
