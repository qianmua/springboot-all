package pres.qm.es.config;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/9  9:38
 * @description :
 */
@Configuration
@RequiredArgsConstructor( onConstructor = @__(@Autowired))
public class ElasticsearchConfig {

    public final ElasticProperties esProperties;

    @Bean
    public RestHighLevelClient clientDev(){
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(
                esProperties.getUsername(), esProperties.getPassword()
        ));
        // 初始化ES客户端的构造器
        RestClientBuilder builder = RestClient.builder(httpHostHandlerDev());
        // 异步的请求配置
        builder.setRequestConfigCallback(builder1 -> {
            // 连接超时时间 默认-1
            builder1.setConnectTimeout(Integer.parseInt(esProperties.getConnectTimeout()));
            //
            builder1.setSocketTimeout(Integer.parseInt(esProperties.getSocketTimeout()));
            // 获取连接的超时时间 默认-1
            builder1.setConnectionRequestTimeout(Integer.parseInt(esProperties.getConnectionRequestTimeout()));
            return builder1;
        });


        // 异步的httpclient连接数配置
        builder.setHttpClientConfigCallback(httpAsyncClientBuilder -> {
            // 最大连接数
            httpAsyncClientBuilder.setMaxConnTotal(Integer.parseInt(esProperties.getMaxConnTotal()));
            // 最大路由连接数
            httpAsyncClientBuilder.setMaxConnPerRoute(Integer.parseInt(esProperties.getMaxConnPerRoute()));
            // 赋予连接凭证
            httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            return httpAsyncClientBuilder;
        });
        return new RestHighLevelClient(builder);
    }


    /**
     * 为了应对集群部署的es，使用以下写法，返回HttpHost数组
     */
    private HttpHost[] httpHostHandlerDev() {
        String[] hosts = esProperties.getHttpHost().split(",");
        HttpHost[] httpHosts = new HttpHost[hosts.length];
        for (int i = 0; i < hosts.length; i++) {
            String ip = hosts[i].split(":")[0];
            int port = Integer.parseInt(hosts[i].split(":")[1]);
            httpHosts[i] = new HttpHost(ip, port, "http");
        }
        return httpHosts;
    }


}
