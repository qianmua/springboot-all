package pres.qm.es.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/9  9:17
 * @description :
 */
@Configuration
@ConfigurationProperties( prefix = "elasticsearch")
@Getter
@Setter
public class ElasticProperties {

    private String connectTimeout;

    private String socketTimeout;

    private String connectionRequestTimeout;

    private String maxConnTotal;

    private String maxConnPerRoute;

    private String username;

    private String password;

    private String httpHost;
}
