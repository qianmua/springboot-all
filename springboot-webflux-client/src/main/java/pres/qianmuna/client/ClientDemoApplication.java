package pres.qianmuna.client;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pres.qianmuna.client.api.UserAPI;
import pres.qianmuna.client.config.ProxyCreator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/16
 * @time 18:31
 */
@SpringBootApplication
@EnableMongoRepositories
public class ClientDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientDemoApplication.class , args);
    }

    @Bean
    FactoryBean<UserAPI> userAPIFactoryBean(ProxyCreator proxyCreator){
        return new FactoryBean<UserAPI>() {
            @Override
            public UserAPI getObject() throws Exception {
                return (UserAPI) proxyCreator.createProxy(this.getObjectType());
            }

            @Override
            public Class<?> getObjectType() {
                return UserAPI.class;
            }
        };
    }
}
