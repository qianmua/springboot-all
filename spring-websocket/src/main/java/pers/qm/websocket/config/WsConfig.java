package pers.qm.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import pers.qm.websocket.handler.WsHandler;
import pers.qm.websocket.interceptor.WsHandshakeInterceptor;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/2/18  16:21
 */
@Configuration
@EnableWebSocket
@ComponentScan( basePackages = "pers.qm.websocket")
public class WsConfig implements WebSocketConfigurer {

    @Autowired
    private WsHandshakeInterceptor wsHandshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsHandler(), "/wsHandler") //注册 handler,这里的 url 要与页面的 url 一致
                .setAllowedOrigins("*") // 允许请求的域名
                .addInterceptors(wsHandshakeInterceptor);  //添加握手拦截

        //旧版浏览器可能不支持 websocket，通过 sockjs 模拟 websocket 的行为，所以下面要配 sockjs 支持。
        registry.addHandler(wsHandler(), "/sockjs").setAllowedOrigins("*")
                .addInterceptors(wsHandshakeInterceptor).withSockJS();
    }


    @Bean
    public WebSocketHandler wsHandler(){
        return new WsHandler();
    }
}
