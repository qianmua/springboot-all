package pres.qianmuna.upload.status;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/10
 * @time 13:38
 */
public enum FileUploadCodeStatus {

    CODE_STATUS_SUCCESS(0),
    CODE_STATUS_FAIL(1);


    private Integer code;

    FileUploadCodeStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
