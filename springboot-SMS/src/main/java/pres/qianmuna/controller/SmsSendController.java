package pres.qianmuna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.service.SendSMSService;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/1
 * @time 22:11
 */
@CrossOrigin
@RestController
public class SmsSendController {
    @Autowired
    private SendSMSService service;

    @Autowired
    private RedisTemplate<String,String> template;

    @GetMapping("/send/{phone}")
    public String send(@PathVariable("phone")String p){
        String code = template.opsForValue().get(p);
        if (!StringUtils.isEmpty(code)){
            return "redis 中存在该玩意";
        }
        // 生成验证码
        code = "1222";
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("code" , code);
        boolean send = service.send(p, "", map);
        if (send){
            template.opsForValue().set(p , code,5 , TimeUnit.SECONDS);
        }
        return "";
    }

}
