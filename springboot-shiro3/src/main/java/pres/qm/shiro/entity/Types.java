package pres.qm.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author qianmuna
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("emp_types")
@ApiModel(value="Types对象", description="")
public class Types implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "t_id", type = IdType.ASSIGN_ID)
    private Long tId;

    @TableField("t_Values")
    private String tValues;


}
