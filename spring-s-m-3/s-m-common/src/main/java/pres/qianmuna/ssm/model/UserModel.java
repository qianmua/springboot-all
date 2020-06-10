package pres.qianmuna.ssm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/10  16:50
 * @description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors( chain = true)
public class UserModel implements Serializable {

    private static final long serialVersionUID = -5420716201302024404L;

    private Long uid;
    private String userName;
    private String password;
    private String updateDate;



}
