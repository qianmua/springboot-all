package pres.qianmuna.mvc2;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/18  16:03
 * @description :
 */
public class Demo {

    public static void main(String[] args) {
        A a = new A("A");
        A b = new A("B");
        m1(a,b);
        System.out.println(a.name);
        System.out.println(b.name);
    }
    static void m1(A a , A b){
        A c;
        c = a;
        a = b;
        b = c;
    }
}
class A{
    String name;

    public A(String name) {
        this.name = name;
    }
}



