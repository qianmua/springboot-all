package pres.qianmuna.reactive.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/30  22:49
 * @description :
 */
public class JavaGui {

    public static void main(String[] args) {
        // gui 理解 callBack

        // 事件 监听
        // 异步的 返回
        // callBack
        // 不阻塞 其他
        //
        // 可同步 可异步



        JFrame jFrame = new JFrame("GUI demo");
        jFrame.setBounds(500,300,400,300);
        BorderLayout layout = new BorderLayout(400, 300);

        jFrame.setLayout(layout);
        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.printf("{ %s} , %d %d \n" , Thread.currentThread().getName() , e.getX() , e.getY());
            }
        });

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("1");
                jFrame.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("2");
                System.exit(0);
            }
        });

        System.out.println("3");
        jFrame.setVisible(true);
    }


}
