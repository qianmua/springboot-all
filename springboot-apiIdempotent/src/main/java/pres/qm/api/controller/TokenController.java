package pres.qm.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qm.api.token.TokenService;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/27  14:46
 * @description :
 */
@RestController
@RequestMapping("token")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    @GetMapping("get")
    public String getToken(){
        return tokenService.createToken();
    }
}
