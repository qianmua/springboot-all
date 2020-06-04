package pres.qianmuna.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pres.qianmuna.spring.mapper.InfoMapper;
import pres.qianmuna.spring.service.InfoService;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/3  21:15
 * @description :
 */
public class MyTest2 {

    @Test
    public void m1(){

        /*
        * 容器中的Bean
        * */
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        // Definition
        String[] names = applicationContext.getBeanDefinitionNames();

        Arrays.stream(names).forEach(System.out::println);

        /// 测试 queryAll
        /*
        * Dao
        * */
        System.out.println("///==========================///");
        InfoMapper infoService = (InfoMapper) applicationContext.getBean("infoMapper");
        infoService.queryAll().forEach(System.out::println);
        // okokokokokokok

        System.out.println("///==========================///");

        /*service -> */
        InfoService infoService2 = (InfoService) applicationContext.getBean("infoService");
        /*  okokokokok */
    }


    @Test
    public void m2(){
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int n = scanner.nextInt();
        int temp = 0 , sum = 0 ;
        for (int i = 0; i < n; i++) {
            temp += (int) (a* Math.pow(10.0 , i));
            sum += temp;
        }
        System.out.println(sum);
    }
}
