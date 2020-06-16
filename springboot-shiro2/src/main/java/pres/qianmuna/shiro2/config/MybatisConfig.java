package pres.qianmuna.shiro2.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/16  22:28
 * @description :
 */
@Configuration
public class MybatisConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor interceptor = new PaginationInterceptor();
        // 分页最大后 返回首页
        interceptor.setOverflow(true);
        //最大单页数量 -1 无限制 默认500
        interceptor.setLimit(60);
        return interceptor;
    }


}
