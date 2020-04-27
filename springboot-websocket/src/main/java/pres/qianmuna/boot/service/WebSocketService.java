package pres.qianmuna.boot.service;

/**
 * Created by yangyibo on 17/1/12.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import pres.qianmuna.boot.entity.ResponseModel;

@Service
public class WebSocketService {

    @Autowired
    //使用SimpMessagingTemplate 向浏览器发送消息
    private SimpMessagingTemplate template;

    public void sendMessage() throws Exception{
        for(int i=0;i<10;i++)
        {
            Thread.sleep(1000);
            template.convertAndSend("/topic/getResponse",new ResponseModel("Welcome !"+i));
            System.out.println("----------------------yangyibo"+i);
        }
    }

}
