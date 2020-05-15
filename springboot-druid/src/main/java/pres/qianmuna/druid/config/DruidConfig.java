package pres.qianmuna.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/15
 * @time 13:38
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties( prefix = "spring.datasource")
    @Bean
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    /**
     * druid 监控
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){

        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid");

        Map<String , String > init = new HashMap<>();

        init.put("loginUsername" , "guest");
        init.put("loginPassword" , "guest");
        init.put("allow" , "");
        init.put("deny" , "拒绝");

        bean.setInitParameters(init);

        return bean;
    }

    /**
     * driud过滤
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String , String > init = new HashMap<>();

        init.put("exclusions" , "*.js,*.css,/druid/*");

        bean.setInitParameters(init);

        ///拦截
        bean.setUrlPatterns(Arrays.asList("/"));

        return bean;
    }
}
