package pres.qianmuna.comm.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Commit2 implements Serializable {
    private static final long serialVersionUID = 4654012619678204421L;

    private Long c2id;
    private Long cid;
    private String commValue;
    private Long fromUid;
    private Long toUid;


}
