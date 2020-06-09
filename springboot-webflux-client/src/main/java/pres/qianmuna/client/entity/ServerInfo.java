package pres.qianmuna.client.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/9  22:18
 * @description :
 */

@Data
@Builder
public class ServerInfo {

    /**
     * 服务器 url
     */
    private String url;
}
