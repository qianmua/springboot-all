package pres.qianmuna.spring.bean;

import pres.qianmuna.spring.aspect.TestAnnotatin;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/8
 * @time 22:11
 */
public interface EProxy {

    @TestAnnotatin("select * from 666")
    public String f();

}
