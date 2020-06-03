package pres.qianmuna.spring.bea1;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/3  18:53
 * @description :
 */
public class Student2 {

    private String name ;
    private String name2;

    public void setName(String name) {
        this.name = name;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public Student2(String name, String name2) {
        this.name = name;
        this.name2 = name2;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "name='" + name + '\'' +
                ", name2='" + name2 + '\'' +
                '}';
    }
}
