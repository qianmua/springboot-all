package pres.qianmuna.jasypt.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pres.qianmuna.jasypt.mapper.PrintInfo;
import pres.qianmuna.jasypt.mapper.PrintInfoService;
import pres.qianmuna.jasypt.service.MoreAInterface;
import pres.qianmuna.jasypt.service.MoreBInterface;

import javax.annotation.PostConstruct;

/**
 * description :
 *
 * @author jinchao.hu
 * @date 2021/5/25  10:29
 */
@Controller
public class Demo {

    private final MoreBInterface moreBInterface;

    private final MoreAInterface<String> moreAInterface;

    @Autowired
    private PrintInfoService printInfoService;

    public Demo(MoreBInterface moreBInterface, MoreAInterface moreAInterface) {
        this.moreBInterface = moreBInterface;
        this.moreAInterface = moreAInterface;
    }


    @PostConstruct
    public void create(){
        moreAInterface.methodAInA("A");
        moreBInterface.methodAInB();

        printInfoService.createUser("????");
    }
}
