package pres.qianmuna.jasypt.controller;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/17
 * @time 12:36
 */
@RestController
public class ProviderController {

    private final StringEncryptor stringEncryptor;

    public ProviderController(StringEncryptor stringEncryptor) {
        this.stringEncryptor = stringEncryptor;
    }

    @Value("${datatype}")
    String value;

    @GetMapping("/g/{v}")
    public String get(@PathVariable String v){
        String str = stringEncryptor.encrypt(v);
        System.out.println(str);
        return str;
    }

    @GetMapping("/get")
    public String get2(){
        return value;
    }


    
}
