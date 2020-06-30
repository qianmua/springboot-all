package pres.qianmuna.mvc3.controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionStage;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/26  10:59
 * @description :
 */
@RestController
public class InitController {
    @GetMapping("/index")
    public String hello(@ModelAttribute ModelMap modelMap , Model model){
        return "hello";
    }

    @GetMapping("/test1")
    public Callable<String> test1(){
        return () -> {
            /// values
            System.out.println("");
            return "test data";
        };
    }
    @GetMapping("/test2")
    public DeferredResult<String> test2(){
        DeferredResult<String> result = new DeferredResult<>(500L);
        result.setErrorResult("666");
        result.onTimeout(() -> System.out.println("6"));
        result.setResult("66666");
        return result;
    }


}
