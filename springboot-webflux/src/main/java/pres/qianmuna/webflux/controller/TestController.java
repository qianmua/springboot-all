package pres.qianmuna.webflux.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    private String timeOut() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "testssssssssss";
    }

}
