package pres.qianmuna.mp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/2  20:33
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode( callSuper = false)
public class QmException extends RuntimeException {

    private static final long serialVersionUID = -1177987981376223623L;
    private String code;

    private String message;

}
