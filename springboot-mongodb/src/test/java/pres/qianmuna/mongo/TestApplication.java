package pres.qianmuna.mongo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import pres.qianmuna.mongodb.dao.CommentRepository;
import pres.qianmuna.mongodb.entity.CommentPO;
import pres.qianmuna.mongodb.service.CommentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/5
 * @time 18:13
 */

@SpringBootTest( classes = {CommentService.class , CommentRepository.class} )
public class TestApplication {

    @Autowired
    private CommentService service;

    @Test
    void find(){
        List<CommentPO> queryAll = service.queryAll();
        queryAll.forEach(System.out::println);
    }
}
