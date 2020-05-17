package pres.qianmuna.mongodb.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/5
 * @time 17:22
 */

// 把一个java类声明为 mongodb的文档
// 对应的是数据库集合名
@Document( collection = "comment")
@Data
//复合索引
//@CompoundIndex
@Accessors( chain = true)
public class CommentPO implements Serializable {

    @Id
    //主键
    private String id;

    @Field( "context")
    //对应mongodb字段名
    private String context;
    private Date date;
    //添加一个单字段索引
    @Indexed
    private String userid;
    private String nickname;
    private LocalDateTime create;
    private Long likenum;
    private Integer replynum;
    private String state;
    private String parentid;
    private String articleid;

}
