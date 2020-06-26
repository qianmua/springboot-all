package pres.qianmuna.mvc3.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/26  10:49
 * @description :
 */
@Configuration
@ComponentScan("pres.*")
@EnableWebMvc
public class AppConfig {

    @Bean
    public ViewResolver initViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * json 转换
     * @return mapping adapter
     */
    /*@Bean( name = "requestMappingHandlerAdapter")
    public HandlerAdapter handlerAdapter(){
        // 适配器
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        // json
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        MediaType type = MediaType.APPLICATION_JSON;
        ArrayList<MediaType> list = new ArrayList<>();
        list.add(type);

        converter.setSupportedMediaTypes(list);

        adapter.getMessageConverters().add(converter);

        return adapter;
    }*/
}
