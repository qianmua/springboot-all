package pres.iqnamuna.rocketmq.transaction;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/30  14:19
 * @description :
 */
public class Init {
    /*
    *
    * 事务 消息 机制
    *
    *
    * */


    public static void main(String[] args) {
//        int sum = 0;
//        for (int i = 1; i <= 100; i++) {
//            if ( (i&1) == 0){
//                sum += i;
//            }
//        }
        //
//        Scanner scanner = new Scanner(System.in);
//        int v1 = scanner.nextInt();
//        String str = v1 >90 ? "优秀" : v1> 80 ? "良好" : v1 > 70 ? "中等" : v1 > 60 ? "及格" : "不及格";
//        System.out.println(str);

        /*for (int i = 1; i <= 1000; i++) {
            if (i%3 == 2 && i%5 == 3 && i%7 == 2)
                System.out.println(i);
        }*/

        // 包装类
        Integer arr[]  = {1,2,3,4,5,6,7,8,9,10};
        Arrays.sort(arr , Collections.reverseOrder());
        Arrays.stream(arr).sorted( Comparator.reverseOrder()).forEach(System.out::println);
    }


}
