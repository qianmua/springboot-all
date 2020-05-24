package pres.qianmuna.oauth.service;

import pres.qianmuna.oauth.model.AuthenticationRequest;
import pres.qianmuna.oauth.model.UserDto;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 22:25
 */
public interface AuthSessionService {


    UserDto auth(AuthenticationRequest request);
}
