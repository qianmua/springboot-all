package pres.qianmuna.spring.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/5/2
 * @time 22:50
 */
public class TankDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //构建容器
        Map<String , Tank> tanks = new HashMap<>(10);
        //坦克名
        String tankName = scanner.next();
        //坐标
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        //方向
        double dire = scanner.nextDouble();
        //速度
        double speed = scanner.nextDouble();
        //等级
        int live = scanner.nextInt();
        //添加到容器
        tanks.put(tankName, new Tank(x,y,dire,speed,live));
        System.out.println(tanks.toString());
    }
    /**
     * 坦克类
     * */
    static class Tank{
        int x;
        int y;
        double dire;
        double speed;
        int live;

        public Tank(int x, int y, double dire, double speed, int live) {
            this.x = x;
            this.y = y;
            this.dire = dire;
            this.speed = speed;
            this.live = live;
        }

        @Override
        public String toString() {
            return "Tank{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dire=" + dire +
                    ", speed=" + speed +
                    ", live=" + live +
                    '}';
        }
    }

}
