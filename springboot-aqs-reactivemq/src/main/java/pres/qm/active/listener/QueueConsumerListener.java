package pres.qm.active.listener;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Controller;
import pres.qm.active.service.GoodsService;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.List;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/10/21  17:02
 * @description :
 */
@Controller
public class QueueConsumerListener {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Resource
    private GoodsService goodsService;

    @JmsListener(destination = "pay.request")
    public void receiveQueue(String name){
        List<Object> list = goodsService.get(name);
        String res = JSON.toJSONString(list);

        System.out.println("res ->"+res);

        jmsMessagingTemplate.convertAndSend(queue , res);
    }
}
