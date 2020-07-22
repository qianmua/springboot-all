package pres.test;

import org.junit.Test;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/7/22  17:44
 * @description :
 */
public class Test1 {

    @Test
    public void m1(){
        StackDemo demo = new StackDemo(5);
        demo.push("A");
        demo.push("66");
        demo.push('c');
        demo.push(1L);
        demo.push(2.00);
//        demo.push(3.00);


        System.out.println(demo.flag);
        demo.pop();
        demo.pop();
        demo.pop();
        demo.pop();
        demo.show();
        System.out.println(demo.flag);
        System.out.println("///////");
        demo.push(2.00);
        demo.show();


    }

    class StackDemo{
        private int maxSize;
        private Object[] stack;
        private int flag = 0;

        public StackDemo(int maxSize) {
            this.maxSize = maxSize;
            stack = new Object[this.maxSize];
        }

        public Object pop(){
            if (empty()){
                System.out.println("the stack is empty.");
                return false;
            }
            return stack[--flag];
        }

        public void show(){
            for (int i = 0; i < flag; i++) {
                System.out.println(stack[i]);
            }
        }


        public boolean push(Object value){
            if (full()){
                System.out.println("the stack full");
                return false;
            }
            stack[flag++] = value;
            return true;
        }

        public boolean full(){
            return flag == maxSize;
        }

        public boolean empty(){
            return flag == 0;
        }





    }
}
