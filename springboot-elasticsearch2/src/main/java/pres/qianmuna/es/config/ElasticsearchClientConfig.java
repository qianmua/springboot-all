package pres.qianmuna.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/12  18:07
 * @description :
 */
@Configuration
public class ElasticsearchClientConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost" , 9200 , "http")));
    }
}
