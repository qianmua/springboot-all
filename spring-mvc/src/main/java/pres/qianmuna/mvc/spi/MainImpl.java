package pres.qianmuna.mvc.spi;

import java.util.ServiceLoader;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/14
 * @time 14:20
 */
public class MainImpl {
    /**
     *
     * JAVA SPI 机制
     * java SPI 服务动态扩展机制
     *
     * 使用spi机制
     * 当服务提供了接口的一种具体实现后
     * 在工程的META-INF/services目录下创建一个用接口全限定名的文件
     * 内容是实现类的全限定名
     *
     * 接口实现类所在工程classpath
     *
     * 主程序通过serviceLoader动态装载实现模块，扫描services目录下的配置文件找到实现类的全限定名
     *
     * 实现类必须有无参构造
     *
     * 可以使用spi 机制实现注册filter servlet listener
     *
     * mvc 模式启动注册类 ，可以做自己的配置扫描
     *
     * mvc设计模式 模板方法
     *
     *
     *
     * web.xml
     * ContextLoaderListener 创建加载了 ioc 的父容器
     *
     * DispatcherServlet 创建加载了子容器
     *
     * 父容器扫描service dao
     *
     * 子容器只扫描 controller
     *
     * 子容器可以关联到父容器 ， 配置时应该分开扫描 ，避免内存消耗
     *
     * applicationContext 扫描 父容器 ， 排除controller 的扫描
     * spring-mvc扫描 子容器 应该只扫描controller
     *
     * */


    /**
     * 实现
     * @param args
     */
    public static void main(String[] args) {

        ServiceLoader<IparseDoc> load = ServiceLoader.load(IparseDoc.class);

        for (IparseDoc iparseDoc : load) {
            System.out.println("-------");
            iparseDoc.parse();
            System.out.println("-------");
        }
    }

}
