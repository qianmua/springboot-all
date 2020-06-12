package pres.qianmuna.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/9  22:18
 * @description :
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfo {

    /**
     * 服务器 url
     */
    private String url;
}
