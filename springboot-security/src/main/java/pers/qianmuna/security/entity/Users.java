package pers.qianmuna.security.entity;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/7
 * @time 16:40
 */
@Data
public class Users {
    private Long userId;
    private String name;
    private String createTime;
    private String updateTime;
    private String version;


}
