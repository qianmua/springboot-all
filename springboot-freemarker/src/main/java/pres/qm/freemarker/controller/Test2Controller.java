package pres.qm.freemarker.controller;

import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pres.qm.freemarker.tools.FreemarkerUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/11/6  16:03
 * @description :
 */
@RestController
@RequestMapping("/api2")
public class Test2Controller {


    @GetMapping("/i")
    public String  a() throws IOException, TemplateException {
        Map<String,Object> map=new HashMap<>();
        map.put("article","1");
        map.put("comment","2");
        map.put("replyLabel","回复");
        String cmtTpl= FreemarkerUtils.getTemplate("test2.ftl",map);
        System.out.println(cmtTpl);

//        result.setCmtTpl(cmtTpl);

        return "success";
    }
}
