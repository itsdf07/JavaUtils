package com.ui;

import javax.swing.*;
import java.awt.*;

/**
 * @Description: TODO
 * @Auther: itsdf07
 * @Date: 2019/8/9/009 14:02
 */
public class BaseJFrame extends JFrame {
    private final int x = 300;
    private final int y = 200;
    private int width = 800;
    private int height = 400;


    public BaseJFrame() {
        //设置窗口初始化的位置(a,b)，和大小(width,height)
        setBounds(x, y, width, height);
    }

    public BaseJFrame(String title) {
        super(title);
        //设置窗口初始化的位置(a,b)，和大小(width,height)
        setBounds(x, y, width, height);
        getContentPane().setBackground(Color.WHITE);
        //设置是否可以调节窗口大小，默认可以
        setResizable(true);
        //设置窗口是否可见，默认是不可见的，这个很重要一定要设置true
        setVisible(true);

    }

    public void doExit() {
        System.exit(0);
    }
}
