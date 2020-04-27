package pres.qianmuna.boot.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pres.qianmuna.boot.pojo.Person;
import pres.qianmuna.boot.service.DemoService;

/**
 * Created by yangyibo on 17/1/13.
 */
@RestController
public class CacheController {

    @Autowired
   private DemoService demoService;

    //http://localhost:8080/put?name=abel&age=23&address=shanghai
    @RequestMapping("/put")
    public Person put(Person person){
        return demoService.save(person);

    }

    //http://localhost:8080/able?id=1
    @RequestMapping("/able")
    @ResponseBody
    public Person cacheable(Person person){


        return demoService.findOne(person);

    }

    //http://localhost:8080/evit?id=1
    @RequestMapping("/evit")
    public String  evit(Long id){
        demoService.remove(id);
        return "ok";

    }


}