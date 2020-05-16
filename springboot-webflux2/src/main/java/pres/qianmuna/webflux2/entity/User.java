package pres.qianmuna.webflux2.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/16
 * @time 14:55
 */
@Document( collection = "user")
@Data
public class User {

    @Id
    private String id;

    private String name;

    private int age;

}
