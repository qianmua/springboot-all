package pres.qianmuna.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/7
 * @time 13:16
 */
@Entity
@Table( name = "users4")
@Data
public class Users {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long proId;
    private String name;
    private String timeZone;
    private String createTime;
    private String updateTime;
    private String version;
}
