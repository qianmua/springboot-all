package pres.qm.kafka.product;

import com.alibaba.fastjson.JSON;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pres.qm.kafka.msg.MsgPo;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/9  0:41
 * @description :
 */

@Component
public class Product {

    @Resource
    private KafkaTemplate<String , Object> kafkaTemplate;

    public void snedMessage(String msg){
        MsgPo msgPo = new MsgPo();
        msgPo.setCode("1")
                .setMessage("hello world : " + msg)
                .setToken(UUID.randomUUID().toString())
                ;

        kafkaTemplate.send("msg" , JSON.toJSONString(msgPo));

    }
}
