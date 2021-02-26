package pers.qm.websocket.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/2/18  16:19
 */
public class WsHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 建立连接
        String connector = (String)session.getAttributes().get("connector");
        System.out.println("已连接："+ connector);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 收到msg
        System.out.println("收到消息：" + message.getPayload());
        session.sendMessage(new TextMessage("收到了，谢谢！")); //给客户端发送数据
        super.handleMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // err link
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // link close
        super.afterConnectionClosed(session, status);
    }
}
