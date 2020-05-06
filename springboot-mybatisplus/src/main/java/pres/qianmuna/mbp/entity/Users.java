package pres.qianmuna.mbp.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/6
 * @time 18:28
 */

@Data
@Accessors( chain = true )
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    //主键策略（uuid 自增 雪花 redis zk）
    // auto //自增
    // none //不使用
    // input手动
    // id_worker 默认全局唯一
    // uuid全局唯一
    //id——worker_str 字符串表示
    @TableId( type = IdType.AUTO)
    private Long utid;
    private String uid;
    private String password;
    private String tel;
    private String name;
    private String address;
    private String addrname;

    //版本号
    @Version
    private Integer version;
    //逻辑删
    @TableLogic
    private Integer deleted;

    //字段填充
    //编写处理器处理即可
    @TableField( fill = FieldFill.INSERT)
    private Date createTime;
    @TableField( fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
