package pres.qianmuna.oauth.model;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 22:27
 */
@Data
public class AuthenticationRequest {

    private String userName;

    private String password;
}
