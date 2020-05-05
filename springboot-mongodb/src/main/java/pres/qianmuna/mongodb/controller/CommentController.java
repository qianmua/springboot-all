package pres.qianmuna.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.mongodb.entity.CommentPO;
import pres.qianmuna.mongodb.service.CommentService;

import javax.xml.stream.events.Comment;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/5
 * @time 18:22
 */
@RestController
public class CommentController {
    @Autowired
    private CommentService service;


    @GetMapping("/mdb/getall")
    public List<CommentPO> queryAll(){
        return service.queryAll();
    }

    @GetMapping("/mdb/save")
    public void save(){
        String name = "test";
        service.saveComment(new CommentPO().setContext(name).setNickname(name));

    }
}
