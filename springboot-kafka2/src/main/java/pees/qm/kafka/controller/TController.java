package pees.qm.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/16  21:11
 * @description :
 */
@RestController
public class TController {


    @Autowired
    private KafkaTemplate kafkaTemplate;

    public static final String topic = "qm";

    @GetMapping("/send/{msg}")
    public String sendMsg(@PathVariable String msg){

        ListenableFuture send = kafkaTemplate.send(topic, msg);

        return "SUCCESS";
    }


    @GetMapping("get/msg")
    public String getMsg(){
        return "???";
    }
    @KafkaListener( id = "" , topics = topic , groupId = "group.test1")
    private String listenerMsg(String msg){
        System.out.println("msg -> " + msg);

        return msg;
    }


    @GetMapping("/tx/{msg}")
    public String txSendMsg(@PathVariable String msg){
        kafkaTemplate.send(topic,msg);

        /// transaction
        kafkaTemplate.executeInTransaction( t -> {
            t.send(topic , "A");

//            throw new RuntimeException("err");

            t.send(topic , "B");

            return true;
        });
        return "success";
    }

    @GetMapping("/tx2/{msg}")
    @Transactional( rollbackFor = RuntimeException.class)
    public String txSendMsg2(@PathVariable String msg){


        kafkaTemplate.send(topic,"A");
//            throw new RuntimeException("err");
        kafkaTemplate.send(topic,"B");
        return "success";
    }

}


