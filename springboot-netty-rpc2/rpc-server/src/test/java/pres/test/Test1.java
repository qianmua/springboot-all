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
        // full
//        demo.push(3.00);
        System.out.println("///////");
        demo.show();
        System.out.println("///////");

        System.out.println(demo.pop());
        System.out.println(demo.pop());
        System.out.println(demo.pop());
        System.out.println(demo.pop());
        System.out.println(demo.pop());
        // empty
//        System.out.println(demo.pop());
        System.out.println("///////");
        demo.push(2.00);
        demo.push(2.00);
        demo.push(2.00);
        demo.push(2.00);
        demo.show();


    }

    class StackDemo{
        // 栈 最大容量
        private int maxSize;
        // 栈 容器
        private Object[] stack;
        // 栈顶指针
        private int flag = 0;

        /**
         * 初始化 栈 容器
         * @param maxSize 栈大小
         */
        public StackDemo(int maxSize) {
            this.maxSize = maxSize;
            stack = new Object[this.maxSize];
        }

        /**
         * 从栈中 弹出 栈顶
         * @return
         */
        public Object pop(){
            // 空栈？
            if (empty()){
                System.out.println("the stack is empty.");
                return null;
            }
            // 栈顶指针 直接 移动就好了
            return stack[--flag];
        }

        /**
         * 打印 数组内元素
         */
        public void show(){
            for (int i = 0; i < flag; i++) {
                System.out.println(stack[i]);
            }
        }

        /**
         * 压栈
         * @param value 压入值
         * @return 成功 || 失败
         */
        public boolean push(Object value){
            // 满？
            if (full()){
                System.out.println("the stack full");
                return false;
            }
            // 移动 指针
            // 放入 数组 直接 替换
            stack[flag++] = value;
            return true;
        }

        /**
         * 满
         * @return ||
         */
        public boolean full(){
            // 比对 标记 位
            // 栈顶 指针 是否 比 最大 大小 大？
            return flag >= maxSize;
        }

        /**
         * 空
         * @return ||
         */
        public boolean empty(){
            // 同理
            return flag <= 0 || flag > this.maxSize;
        }





    }
}
