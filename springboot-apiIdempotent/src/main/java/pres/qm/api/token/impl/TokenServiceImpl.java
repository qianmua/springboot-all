package pres.qm.api.token.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerResponse;
import pres.qm.api.token.TokenService;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/27  14:10
 * @description :
 */
@Service
public class TokenServiceImpl implements TokenService {

    public static final String TOKEN_NAME = "token";

    // redistemplate
    @Autowired
    private RedisTemplate<String , Serializable> redisTemplate;

    @Override
    public ServerResponse createToken() {
        String str = UUID.randomUUID().toString().replace("-" , "");

        // 拼接 token
        // 使用 StringBuffer // 线程安全
        StringBuffer token = new StringBuffer();
        token.append("API:").append(str);
        redisTemplate.opsForValue().set(token.toString() , token.toString() , 1800 , TimeUnit.SECONDS);
        // return token
        return ServerResponse.ok().body(token);
    }

    @Override
    public void checkToken(HttpServletRequest request) {

        String token = request.getHeader(TOKEN_NAME);
        // header
        if (StringUtils.isBlank(token)){
            // 参数
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isBlank(token)){
                // 参数中不存在
                // 该干嘛干嘛
                throw new RuntimeException("token异常");
            }
        }

        // NPE
        if(!redisTemplate.hasKey(token)){
            // redis 中不存在存在token
            throw new RuntimeException("token 不存在");
        }

        // delete
        Boolean delete = redisTemplate.delete(token);
        if (delete != null && !delete){
            throw new RuntimeException("删除token")
        }


    }
}
