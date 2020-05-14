package pres.qianmuna.aop.proxy;

import pres.qianmuna.aop.entity.UserBean;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/14
 * @time 17:49
 */
public class ProxyText {

    /**
     * 代理对象必须是接口
     * @param args
     */

    public static void main(String[] args) {
        byte[] proxyClass = ProxyGenerator.generateProxyClass("$Proxy", new Class[]{UserBean.class});

        try {
            FileOutputStream outputStream = new FileOutputStream("ProxyTest1.class");
            outputStream.write(proxyClass);
            outputStream.flush();
            outputStream.close();
            System.out.println("ending");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
