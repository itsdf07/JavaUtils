package com.ui;

import com.utils.Transform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Description: TODO
 * @Auther: itsdf07
 * @Date: 2019/8/14/014 14:08
 */
public class MainJFrame2Rchat9x07 extends BaseJFrame {
    public static void main(String[] args) {
        MainJFrame2Rchat9x07 frame = new MainJFrame2Rchat9x07("9X07工具");
        frame.showUI();
    }

    public MainJFrame2Rchat9x07(String title) {
        super(title);
    }

    public void showUI() {
        Font font = new Font("黑体", Font.PLAIN, 20);//设置字体

//        add(initPanelDemical2Hex(font));
//        add(initPanelHex2Demical(font));
//        add(initPanelHex2String(font));
//        add(initPanelCn2Unicode(font));
        add(initPanelAT2Poc021234(font));
        add(initPanelPoc2AT091234(font));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));//居左排列
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);//窗体在屏幕上居中显示
    }


    /**
     * 4.2 请求Poc参数AT转换成可读内容
     * 例子:AT+POC=021234
     * 指令  结果   操作ID   当前使用的参数
     * 02    00    1234      69703d302e302e302e303b69643d74657374313b00
     * 备注:对当前使用的参数进行处理
     *
     * @param font
     * @return
     */
    private JPanel initPanelAT2Poc021234(Font font) {
        JPanel jPanelAT2Poc021234 = new JPanel();
        JLabel jLabelAT2Poc021234 = new JLabel("4.2-ERequestGetParam:");
        JTextArea jTextAreaAT2Poc021234Value = new JTextArea(1, 16);
        jTextAreaAT2Poc021234Value.setFont(font);
        JButton jBtnAT2Poc021234 = new JButton("》");
        JTextArea jTextAreaAT2Poc021234Result = new JTextArea(1, 16);
        jTextAreaAT2Poc021234Result.setFont(font);
        jBtnAT2Poc021234.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaAT2Poc021234Value.getText();
                String transResult = Transform.hex2String(transValue);
                jTextAreaAT2Poc021234Result.setText(transResult + "");
            }
        });
        jPanelAT2Poc021234.add(jLabelAT2Poc021234);
        jPanelAT2Poc021234.add(jTextAreaAT2Poc021234Value);
        jPanelAT2Poc021234.add(jBtnAT2Poc021234);
        jPanelAT2Poc021234.add(jTextAreaAT2Poc021234Result);
        return jPanelAT2Poc021234;
    }

    /**
     * 4.4 请求 POC 应用进入群组AT转换成可读内容
     * 例子:AT+POC=091234
     * 指令  操作ID  组ID
     * 09   1234     00000FA6
     * 备注:对群组ID（如4006）转成AT使用 0FA6
     *
     * @param font
     * @return
     */
    private JPanel initPanelPoc2AT091234(Font font) {
        JPanel jPanelPoc2AT091234 = new JPanel();
        JLabel jLabelPoc2AT091234 = new JLabel("4.4-ERequestEnterGroup:");
        JTextArea jTextAreaPoc2AT091234Value = new JTextArea(1, 16);
        jTextAreaPoc2AT091234Value.setFont(font);
        JButton jBtnPoc2AT091234 = new JButton("》");
        JTextArea jTextAreaPoc2AT091234Result = new JTextArea(1, 16);
        jTextAreaPoc2AT091234Result.setFont(font);
        jBtnPoc2AT091234.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaPoc2AT091234Value.getText();
                if (!transValue.matches("[0-9]*")) {
                    jTextAreaPoc2AT091234Value.setText("");
                    jTextAreaPoc2AT091234Result.setText("");
                    return;
                }
                String transResult = Transform.demical2Hex(Integer.parseInt(transValue));
                while (transResult.length() < 8) {
                    transResult = "0" + transResult;
                }
                jTextAreaPoc2AT091234Result.setText(transResult.toUpperCase() + "");
            }
        });
        jPanelPoc2AT091234.add(jLabelPoc2AT091234);
        jPanelPoc2AT091234.add(jTextAreaPoc2AT091234Value);
        jPanelPoc2AT091234.add(jBtnPoc2AT091234);
        jPanelPoc2AT091234.add(jTextAreaPoc2AT091234Result);
        return jPanelPoc2AT091234;
    }


}
