package pers.qm.spi.chain.handler.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/2/18  9:20
 */
@AllArgsConstructor
@NoArgsConstructor
public class Context {

    private String code;
    private String msg;


    @Override
    public String toString() {
        return "Context{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
