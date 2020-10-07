package pres.qm.date.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/7  14:38
 * @description :
 */
@Data
public class Users {
    private Long utid;
    private String uid;
    private String password;
    private String tel;
    private String name;
    private String address;
    private String addrName;

//    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat( pattern = "yyyy-MM-dd")
    private LocalDateTime update_time;
    private Integer version;
    private Integer deleted;
}
