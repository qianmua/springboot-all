package pers.qm.socket.util;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

/**
 * description :
 *
 * @author mu.qian
 * @date 2021/9/23  10:16
 */
@Component
public class EchoHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(final WebSocketSession session) {
        return session.send(
                session
                        .receive()
                        .map(msg -> session.textMessage("msg:" + msg.getPayloadAsText())));

    }
}
