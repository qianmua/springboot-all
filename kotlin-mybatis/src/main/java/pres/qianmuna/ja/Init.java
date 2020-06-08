package pres.qianmuna.ja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/8  17:40
 * @description :
 */
public class Init {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>(Arrays.asList(1,2,3,4));
        set.forEach(System.out::println);
        System.out.println("//=========//");
        set = set.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(HashSet::new));
        set.forEach(System.out::println);
        System.out.println("//=========//");
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4));
        list = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(ArrayList::new));
        list.forEach(System.out::println);
    }
}
