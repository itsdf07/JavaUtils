package com.ui;

import com.utils.Transform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Description: TODO
 * @Auther: itsdf07
 * @Date: 2019/8/9/009 14:10
 */
public class MainJFrame extends BaseJFrame {
    private final String[] buttonName = {"十进制->十六进制",
            "中文->Unicode",
            "Unicode->中文",
            "9x07:中文->Unicode",
            "9x07:Unicode-中文"};

    public MainJFrame(String title) {
        super(title);
    }

    public void showUI() {
        Font font = new Font("黑体", Font.PLAIN, 20);//设置字体

        add(initPanelDemical2Hex(font));
        add(initPanelHex2Demical(font));
        add(initPanelCn2Unicode(font));
        add(initPanelUnicode2Cn(font));

//        //this.setLayout(new FlowLayout());//居中排列
        this.setLayout(new FlowLayout(FlowLayout.LEFT));//居左排列
//        //this.setLayout(new FlowLayout(FlowLayout.RIGHT));//居右
//        //this.setLayout(new FlowLayout(FlowLayout.CENTER));// 居中
//        //this.setLayout(new FlowLayout(FlowLayout.LEADING));//居左
//        //this.setLayout(new FlowLayout(FlowLayout.TRAILING));//居右
//        //https://blog.csdn.net/jasnet_u/article/details/82941979
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);//窗体在屏幕上居中显示
    }


    /*******************************************************************************/
    /**
     * 初始化十进制转换成十六进制的界面及事件
     *
     * @param font 字体
     * @return
     */
    private JPanel initPanelDemical2Hex(Font font) {
        JPanel jPanelDemical2Hex = new JPanel();
        JLabel jLabelDemical2Hex = new JLabel("十进制》十六进制:");
        JTextArea jTextAreaDemical2HexValue = new JTextArea(1, 16);
        jTextAreaDemical2HexValue.setFont(font);
        JButton jBtnDemical2Hex = new JButton("》");
        JTextArea jTextAreaDemical2HexResult = new JTextArea(1, 16);
        jTextAreaDemical2HexResult.setFont(font);
        jBtnDemical2Hex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaDemical2HexValue.getText();
                if (!transValue.matches("[0-9]*")) {
                    jTextAreaDemical2HexValue.setText("");
                    jTextAreaDemical2HexResult.setText("");
                    return;
                }
                String transResult = Transform.demical2Hex(Integer.parseInt(transValue));
                jTextAreaDemical2HexResult.setText(transResult);
            }
        });
        jPanelDemical2Hex.add(jLabelDemical2Hex);
        jPanelDemical2Hex.add(jTextAreaDemical2HexValue);
        jPanelDemical2Hex.add(jBtnDemical2Hex);
        jPanelDemical2Hex.add(jTextAreaDemical2HexResult);
        return jPanelDemical2Hex;
    }

    /**
     * 初始化十六进制转换成十进制的界面及事件
     *
     * @param font 字体
     * @return
     */
    private JPanel initPanelHex2Demical(Font font) {
        JPanel jPanelHex2Demical = new JPanel();
        JLabel jLabelHex2Demical = new JLabel("十六进制》十进制:");
        JTextArea jTextAreaHex2DemicalValue = new JTextArea(1, 16);
        jTextAreaHex2DemicalValue.setFont(font);
        JButton jBtnHex2Demical = new JButton("》");
        JTextArea jTextAreaHex2DemicalResult = new JTextArea(1, 16);
        jTextAreaHex2DemicalResult.setFont(font);
        jBtnHex2Demical.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaHex2DemicalValue.getText();
                int transResult = Transform.hex2decimal(transValue);
                jTextAreaHex2DemicalResult.setText(transResult + "");
            }
        });
        jPanelHex2Demical.add(jLabelHex2Demical);
        jPanelHex2Demical.add(jTextAreaHex2DemicalValue);
        jPanelHex2Demical.add(jBtnHex2Demical);
        jPanelHex2Demical.add(jTextAreaHex2DemicalResult);
        return jPanelHex2Demical;
    }

    /**
     * 初始化中文转成Unicode
     *
     * @param font
     * @return
     */
    private JPanel initPanelCn2Unicode(Font font) {
        JPanel jPanelCn2Unicode = new JPanel();
        JLabel jLabelCn2Unicode = new JLabel("中文》Unicode:");
        JTextArea jTextAreaCn2UnicodeValue = new JTextArea(1, 16);
        jTextAreaCn2UnicodeValue.setFont(font);
        JButton jBtnCn2Unicode = new JButton("》");
        JTextArea jTextAreaCn2UnicodeResult = new JTextArea(1, 16);
        jTextAreaCn2UnicodeResult.setFont(font);
        jBtnCn2Unicode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaCn2UnicodeValue.getText();
                String transResult = Transform.cn2Unicode(transValue);
                jTextAreaCn2UnicodeResult.setText(transResult + "");
            }
        });
        jPanelCn2Unicode.add(jLabelCn2Unicode);
        jPanelCn2Unicode.add(jTextAreaCn2UnicodeValue);
        jPanelCn2Unicode.add(jBtnCn2Unicode);
        jPanelCn2Unicode.add(jTextAreaCn2UnicodeResult);
        return jPanelCn2Unicode;
    }

    /**
     * 初始化Unicode转成中文
     *
     * @param font
     * @return
     */
    private JPanel initPanelUnicode2Cn(Font font) {
        JPanel jPanelUnicode2Cn = new JPanel();
        JLabel jLabelUnicode2Cn = new JLabel("Unicode》中文:");
        JTextArea jTextAreaUnicode2CnValue = new JTextArea(1, 16);
        jTextAreaUnicode2CnValue.setFont(font);
        JButton jBtnUnicode2Cn = new JButton("》");
        JTextArea jTextAreaUnicode2CnResult = new JTextArea(1, 16);
        jTextAreaUnicode2CnResult.setFont(font);
        jBtnUnicode2Cn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaUnicode2CnValue.getText();
                String transResult = Transform.unicode2Cn(transValue);
                jTextAreaUnicode2CnResult.setText(transResult + "");
            }
        });
        jPanelUnicode2Cn.add(jLabelUnicode2Cn);
        jPanelUnicode2Cn.add(jTextAreaUnicode2CnValue);
        jPanelUnicode2Cn.add(jBtnUnicode2Cn);
        jPanelUnicode2Cn.add(jTextAreaUnicode2CnResult);
        return jPanelUnicode2Cn;
    }


}
