package pres.qianmuna.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/4
 * @time 15:58
 */
@Configuration
public class RedisTemplateConfig {

    /**
     *
     * 序列化配置
     * 对象要序列化，传输
     *
     * */
    @Bean
    public RedisTemplate<String, Object> redisTemplate (RedisConnectionFactory factory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        //连接工厂
        redisTemplate.setConnectionFactory(factory);
        // 配置序列化
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);

        redisTemplate.setKeySerializer(serializer);

        return redisTemplate;
    }
}
