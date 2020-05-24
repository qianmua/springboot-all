package pres.qianmuna.oauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/24
 * @time 22:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors( chain = true)
public class UserDto {

    private String id;
    private String userName;
    private String fullName;
    private String tel;


    private Set<String> auth;
}
