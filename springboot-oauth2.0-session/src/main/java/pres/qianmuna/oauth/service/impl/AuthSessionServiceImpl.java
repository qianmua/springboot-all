package pres.qianmuna.oauth.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pres.qianmuna.oauth.model.AuthenticationRequest;
import pres.qianmuna.oauth.model.UserDto;
import pres.qianmuna.oauth.service.AuthSessionService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 22:29
 */
@Service
public class AuthSessionServiceImpl implements AuthSessionService {
    @Override
    public UserDto auth(AuthenticationRequest request) {

        if (request == null
                || StringUtils.isEmpty(request.getPassword())
                || StringUtils.isEmpty(request.getUserName()))
            throw new RuntimeException("id and password exception");


        UserDto dto = get(request.getUserName());

        if (dto == null)
            throw new RuntimeException("empty");

        if ( !request.getPassword().equals(dto.getId()))
            throw new RuntimeException("password err ");

        return dto;
    }

    public UserDto get(String username){
        return userMap.get(username);
    }

    private Map<String , UserDto> userMap = new HashMap<>();
    {
        userMap.put("101" , new UserDto("111" , "222","222","444"));
        userMap.put("202" , new UserDto("222222" , "222","222","444"));
    }
}
