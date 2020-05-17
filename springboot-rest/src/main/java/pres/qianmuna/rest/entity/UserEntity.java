package pres.qianmuna.rest.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/17
 * @time 13:41
 */
@Document( collection = "usertest")
public class UserEntity implements Serializable {

    @Id
    private String id;

    private String name;
    private String time;
    private int version;
}
