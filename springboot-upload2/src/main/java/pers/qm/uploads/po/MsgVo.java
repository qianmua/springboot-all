package pers.qm.uploads.po;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/3/18  15:08
 */
@Data
@Accessors( chain = true)
public class MsgVo<B> implements Serializable {

    private static final long serialVersionUID = -4554472231406528752L;

    private String code ;

    private B data;

    public <B> MsgVo<B> success(){
        return new MsgVo<B>().setCode("200");
    }

    public <B> MsgVo<B> success(B body){
        return new MsgVo<B>().setCode("200")
                .setData(body);
    }


}
