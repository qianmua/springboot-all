package pres.qm.shiro.utils;

import java.util.Random;

public class SaltUtils {

    /**
     * 生成salt的静态方法
     * @param n 盐次数
     * @return 盐值
     */
    public static String getSalt(int n){

        char[] chars = "ABCDEFGHIGKLMNOPQRSTUVWXVZabcdefghigklmnopqrstuvwxvz01234567890!@#$%^&*()".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    /*public static void main(String[] args) {
        String salt = getSalt(4);
        System.out.println(salt);
    }*/
}
