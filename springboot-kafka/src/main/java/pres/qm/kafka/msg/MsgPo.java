package pres.qm.kafka.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/9  0:42
 * @description :
 */
@Data
@Accessors( chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MsgPo implements Serializable {

    private String code;
    private String message;
    private String token;
}
