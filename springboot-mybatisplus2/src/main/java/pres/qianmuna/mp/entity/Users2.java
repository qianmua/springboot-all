package pres.qianmuna.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author qianmuna
 * @since 2020-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Users2对象", description="")
public class Users2 implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "utid", type = IdType.AUTO)
    private Integer utid;

    private String uid;

    private String password;

    private String tel;

    private String name;

    private String address;

    @TableField("addrName")
    private String addrName;

    @TableField( exist = false)
    private Object o;


}
