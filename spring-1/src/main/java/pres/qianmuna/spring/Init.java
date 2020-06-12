package pres.qianmuna.spring;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/3  18:20
 * @description :
 */
public class Init {
}


interface Fly{
    void say();
    void land();
    void fly();
}

abstract class Animal{
    abstract void eat();
}

abstract class Vehicle{ }

class AVeh extends Vehicle implements Fly{
    @Override
    public void say() {

    }
    @Override
    public void land() {

    }
    @Override
    public void fly() {

    }
}
class Super extends Animal implements Fly{
    @Override
    public void say() {

    }
    @Override
    public void land() {

    }
    @Override
    public void fly() {

    }
    @Override
    void eat() {

    }
    void leap(){}
    void stop(){}
}

class Bird extends Animal implements Fly{
    @Override
    public void say() {

    }
    @Override
    public void land() {

    }
    @Override
    public void fly() {

    }
    @Override
    void eat() {

    }
    void build(){}
    void lay(){}
}
