package pres.qianmuna.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pres.qianmuna.webflux.entity.User;
import pres.qianmuna.webflux.mapper.UserMapping;
import pres.qianmuna.webflux.mapper.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/16
 * @time 13:39
 */
@RestController
@Slf4j
public class TestController {

    /*
    * 使用构造方法的形式
    * 降低与 spring 的耦合度
    *
    * 返回多条数据使用 flux
    * 单条使用 mono
    *
    * */
//    @Autowired
    private final UserMapping userMapping;
    private final UserRepository userRepository;

    public TestController(UserMapping userMapping, UserRepository userRepository) {
        this.userMapping = userMapping;
        this.userRepository = userRepository;
    }

    /**
     * 非阻塞
     * @return
     */
    @GetMapping("/test")
    public Mono<String> test(){
        log.info("get start test1");
        Mono<String> mono = Mono.fromSupplier(this::timeOut);
        log.info("get start test1");
        return mono;
    }


    /**
     * 阻塞
     * @return
     */
    @GetMapping("/test2")
    public String test2(){
        log.info("get start test2");
        String str = timeOut();
        log.info("get start test2");

        return str;
    }

    /**
     * 流式返回
     * // produces = "text/event-stream" 说明返回时一个流
     * // produces = MediaType.TEXT_EVENT_STREAM_VALUE 或者用它定义好的
     * @return
     */
    @GetMapping(value = "/test3" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux(){
        Flux<String> flux = Flux.fromStream(IntStream.range(1, 10).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "flux" + i;
        }));
        return flux;
    }

    @GetMapping("/test4")
    public Flux<List<Map<String,String>>> getAll(){
        return null;
    }

    /*
    * mongo db
    * */

    /**
     * 一次性全部
     * @return
     */
    @GetMapping("/test5")
    public Flux<User> queryAll(){
        return userRepository.findAll();
    }

    /**
     * 流返回
     * @return
     */
    @GetMapping(value = "/test6" ,produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> queryAll2(){
    return this.userRepository.findAll();
    }

    /**
     * 保存
     * @param user
     * @return
     */
    @GetMapping(value = "/test7")
    public Mono<User> create(User user){
        return this.userRepository.save(user);

    }

    /**
     * delete 返回状态码
     *
     * ResponseEntity
     *  原生返回
     * @param id
     * @return
     */
    @DeleteMapping("/test8/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
        // 判断数据存在并删除
        //deleteById 是没有返回值的
        return this.userRepository.findById(id)
                //map 和 flatmap
                //只转换数据据map
                //操作数据并且返回用flatmap
                .flatMap( user -> this.userRepository.delete(user)
                        //有返回值 和 没有返回值
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 修改返回新的数据
     * @return
     */
    @PutMapping("/test9/{id}")
    public Mono<ResponseEntity<User>> update(@PathVariable("id") String id , User user){
        /*
        * 存在 返回 修改后 数据 200状态码 ， 不存在返回404
        * */

        return this.userRepository.findById(id)
                // 修改数据 返回 mono
                .flatMap(user1 -> {
                    // save 时候没有id就会发生新增操作
                    user1.setAge(user.getAge());
                    user1.setName(user.getName());
                    return this.userRepository.save(user1);
                })
                //转换返回数据 将mono 转换位 ResponseEntity
                .map( user1 -> new ResponseEntity<>(user1, HttpStatus.OK))
                // 404
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * 通过Id 查找
     * @param id
     * @return
     */
    public Mono<ResponseEntity<User>> queryById(@PathVariable String id){

        return this.userRepository.findById(id)
                .map(user -> new ResponseEntity<User>(user , HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/getage/{s}/{e}")
    public Flux<User> findTest(@PathVariable("s")int s ,
                                               @PathVariable("e") int e){
        return this.userRepository.findByAgeBetween(s,e);

    }
    /**
     * test time out
     * @return
     */
    private String timeOut() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "testssssssssss";
    }

}
