package pres.qianmuna.jpa.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/17
 * @time 12:57
 */
@Configuration
public class JpaDataSourcesConfig {

    @Primary
    @Bean("primary")
    @Qualifier("primary")
    @ConfigurationProperties( prefix = "spring.datasource.primary")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("secondary")
    @Qualifier("secondary")
    @ConfigurationProperties( prefix = "spring.datasource.secondary")
    public DataSource dataSource2(){
        return DataSourceBuilder.create().build();
    }
}
