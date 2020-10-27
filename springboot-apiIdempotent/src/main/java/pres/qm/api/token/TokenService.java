package pres.qm.api.token;

import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/27  14:08
 * @description :
 */
public interface TokenService {
    ServerResponse createToken();

    void checkToken(HttpServletRequest request);
}
