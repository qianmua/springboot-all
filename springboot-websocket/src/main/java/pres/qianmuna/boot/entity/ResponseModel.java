package pres.qianmuna.boot.entity;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/4/27
 * @time 14:33
 */
public class ResponseModel {

    private String responseMessage;

    public ResponseModel(String responseMessage){
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    ResponseModel(){}

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
