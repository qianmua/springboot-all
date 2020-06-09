package pres.qianmuna.client.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/9  22:22
 * @description :
 */
@Data
@Builder
public class MethodsInfo {
    /*
    * 方法调用信息
    * */

    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求方法
     * */
    private HttpMethod method;

    /**
     * url参数
     * */
    private Map<String , Object> params;

    /**
     * 请求体
     * */
    private Mono<?> body;
}
