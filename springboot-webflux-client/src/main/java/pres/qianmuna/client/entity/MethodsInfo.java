package pres.qianmuna.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
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
    private Mono body;

    /**
     * 请求body类型
     */
    private Class<?> bodyElementType;


    /**
     * 返回结果类型
     */
    private boolean returnFlux;

    /**
     * 返回对象类型
     */
    private Class<?> elementType;
}
