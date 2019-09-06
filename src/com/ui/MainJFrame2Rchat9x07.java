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

        add(initPanelAT2Poc021234(font));
        add(initPanelAT2Poc091234(font));
        add(initPanelAT2Poc111234(font));
        add(initPanelAT2Poc281234(font));
        add(initPanelAT2Poc7D1234(font));
        add(initPanelAT2Poc80011234(font));
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
        JButton jBtnPoc2AT021234 = new JButton("《");
        JTextArea jTextAreaAT2Poc021234Result = new JTextArea(1, 16);
        jTextAreaAT2Poc021234Result.setFont(font);
        jBtnAT2Poc021234.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaAT2Poc021234Value.getText().trim();
                String transResult = Transform.hex2String(transValue);
                jTextAreaAT2Poc021234Result.setText(transResult + "");
            }
        });
        jBtnPoc2AT021234.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaAT2Poc021234Result.getText().trim();
                String transResult = Transform.stringToHex(transValue);
                jTextAreaAT2Poc021234Value.setText(transResult + "00");
            }
        });
        jPanelAT2Poc021234.add(jLabelAT2Poc021234);
        jPanelAT2Poc021234.add(jTextAreaAT2Poc021234Value);
        jPanelAT2Poc021234.add(jBtnAT2Poc021234);
        jPanelAT2Poc021234.add(jBtnPoc2AT021234);
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
    private JPanel initPanelAT2Poc091234(Font font) {
        JPanel jPanelPoc2AT091234 = new JPanel();
        JLabel jLabelPoc2AT091234 = new JLabel("4.4-ERequestEnterGroup:");
        JTextArea jTextAreaPoc2AT091234Value = new JTextArea(1, 16);
        jTextAreaPoc2AT091234Value.setFont(font);
        JButton jBtnPoc2AT091234 = new JButton("》");
        JButton jBtnAT2Poc091234 = new JButton("《");
        JTextArea jTextAreaPoc2AT091234Result = new JTextArea(1, 16);
        jTextAreaPoc2AT091234Result.setFont(font);
        jBtnPoc2AT091234.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaPoc2AT091234Value.getText().trim();
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
        jBtnAT2Poc091234.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaPoc2AT091234Result.getText().trim();
                if (!transValue.matches("[0-9a-fA-F]*")) {
                    jTextAreaPoc2AT091234Value.setText("");
                    jTextAreaPoc2AT091234Result.setText("");
                    return;
                }
                long transResult = Transform.hex2decimal(transValue);
                jTextAreaPoc2AT091234Value.setText(transResult + "");
            }
        });
        jPanelPoc2AT091234.add(jLabelPoc2AT091234);
        jPanelPoc2AT091234.add(jTextAreaPoc2AT091234Value);
        jPanelPoc2AT091234.add(jBtnPoc2AT091234);
        jPanelPoc2AT091234.add(jBtnAT2Poc091234);
        jPanelPoc2AT091234.add(jTextAreaPoc2AT091234Result);
        return jPanelPoc2AT091234;
    }


    /**
     * 4.9 上报GPS位置信息AT转换成可读内容
     * 例子:AT+POC=111234
     * 指令  操作ID   位置类型   经纬度
     * 11    1234     00         3131362e33382c33392e393000
     * 备注:对群组ID（如4006）转成AT使用 0FA6
     *
     * @param font
     * @return
     */
    private JPanel initPanelAT2Poc111234(Font font) {
        JPanel jPanelPoc2AT111234 = new JPanel();
        JLabel jLabelPoc2AT111234 = new JLabel("4.9-ERequestSetGPS:");
        JTextArea jTextAreaPoc2AT111234Value = new JTextArea(1, 16);
        jTextAreaPoc2AT111234Value.setFont(font);
        JButton jBtnPoc2AT111234 = new JButton("》");
        JButton jBtnAT2Poc111234 = new JButton("《");
        JTextArea jTextAreaPoc2AT111234Result = new JTextArea(1, 16);
        jTextAreaPoc2AT111234Result.setFont(font);
        jBtnPoc2AT111234.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaPoc2AT111234Value.getText().trim();
                String transResult = Transform.hex2String(transValue);
                jTextAreaPoc2AT111234Result.setText(transResult.toUpperCase());
            }
        });
        jBtnAT2Poc111234.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaPoc2AT111234Result.getText().trim();
                String transResult = Transform.stringToHex(transValue);
                jTextAreaPoc2AT111234Value.setText(transResult + "00");
            }
        });
        jPanelPoc2AT111234.add(jLabelPoc2AT111234);
        jPanelPoc2AT111234.add(jTextAreaPoc2AT111234Value);
        jPanelPoc2AT111234.add(jBtnPoc2AT111234);
        jPanelPoc2AT111234.add(jBtnAT2Poc111234);
        jPanelPoc2AT111234.add(jTextAreaPoc2AT111234Result);
        return jPanelPoc2AT111234;
    }


    /**
     * 4.18 获取北京时间 AT转换成可读内容
     * 例子:AT+POC=111234
     * 指令  结果   操作ID   时间
     * 28    00     1234     13040209221e02
     * 备注:对群组ID（如4006）转成AT使用 0FA6
     *
     * @param font
     * @return
     */
    private JPanel initPanelAT2Poc281234(Font font) {
        JPanel jPanelPoc2AT281234 = new JPanel();
        JLabel jLabelPoc2AT281234 = new JLabel("4.18-ERequestGetTime:");
        JTextArea jTextAreaPoc2AT281234Value = new JTextArea(1, 16);
        jTextAreaPoc2AT281234Value.setFont(font);
        JButton jBtnPoc2AT281234 = new JButton("》");
        JButton jBtnAT2Poc281234 = new JButton("《");
        JTextArea jTextAreaPoc2AT281234Result = new JTextArea(1, 16);
        jTextAreaPoc2AT281234Result.setFont(font);
        jBtnPoc2AT281234.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaPoc2AT281234Value.getText().trim();
                if (transValue.isEmpty()) {
                    return;
                }
                if ((transValue.length() % 2) != 0 && (transValue.length() / 2) < 7) {
                    System.out.println("AT返回时间长度异常,date:" + transValue);
                    return;
                }
                String year = "20" + Transform.hex2decimal(transValue.substring(0, 2));
                String month = Transform.hex2decimal(transValue.substring(2, 4)) + "";
                if (month.length() < 2) {
                    month = "0" + month;
                }
                String day = Transform.hex2decimal(transValue.substring(4, 6)) + "";
                if (day.length() < 2) {
                    day = "0" + day;
                }
                String time = Transform.hex2decimal(transValue.substring(6, 8)) + "";
                if (time.length() < 2) {
                    time = "0" + time;
                }
                String minute = Transform.hex2decimal(transValue.substring(8, 10)) + "";
                if (minute.length() < 2) {
                    minute = "0" + minute;
                }
                String second = Transform.hex2decimal(transValue.substring(10, 12)) + "";
                if (second.length() < 2) {
                    second = "0" + second;
                }
                String week = Transform.hex2decimal(transValue.substring(12, 14)) + "";
                if (week.length() < 2) {
                    week = "0" + week;
                }
                jTextAreaPoc2AT281234Result.setText(year + month + day + time + minute + second + week);
            }
        });
        jBtnAT2Poc281234.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaPoc2AT281234Result.getText().trim();
                if (transValue.isEmpty()) {
                    return;
                }
                transValue = transValue.substring(2, transValue.length());
                if ((transValue.length() % 2) != 0 && (transValue.length() / 2) < 7) {
                    System.out.println("转成AT时间长度异常,date:" + transValue);
                    return;
                }
                String year = Transform.demical2Hex(Integer.parseInt(transValue.substring(0, 2)));
                if (year.length() < 2) {
                    year = "0" + year;
                }
                String month = Transform.demical2Hex(Integer.parseInt(transValue.substring(2, 4)));
                if (month.length() < 2) {
                    month = "0" + month;
                }
                String day = Transform.demical2Hex(Integer.parseInt(transValue.substring(4, 6)));
                if (day.length() < 2) {
                    day = "0" + day;
                }
                String time = Transform.demical2Hex(Integer.parseInt(transValue.substring(6, 8)));
                if (time.length() < 2) {
                    time = "0" + time;
                }
                String minute = Transform.demical2Hex(Integer.parseInt(transValue.substring(8, 10)));
                if (minute.length() < 2) {
                    minute = "0" + minute;
                }
                String second = Transform.demical2Hex(Integer.parseInt(transValue.substring(10, 12)));
                if (second.length() < 2) {
                    second = "0" + second;
                }
                String week = Transform.demical2Hex(Integer.parseInt(transValue.substring(12, 14)));
                if (week.length() < 2) {
                    week = "0" + week;
                }
                jTextAreaPoc2AT281234Value.setText(year + month + day + time + minute + second + week);
            }
        });
        jPanelPoc2AT281234.add(jLabelPoc2AT281234);
        jPanelPoc2AT281234.add(jTextAreaPoc2AT281234Value);
        jPanelPoc2AT281234.add(jBtnPoc2AT281234);
        jPanelPoc2AT281234.add(jBtnAT2Poc281234);
        jPanelPoc2AT281234.add(jTextAreaPoc2AT281234Result);
        return jPanelPoc2AT281234;
    }


    /**
     * 4.20 查看POC版本AT转换成可读内容
     * 例子:AT+POC=7D1234
     * 指令  结果   操作ID   版本信息
     * 7D    00     1234     52323031393034313000
     * 备注:对当前使用的参数进行处理
     *
     * @param font
     * @return
     */
    private JPanel initPanelAT2Poc7D1234(Font font) {
        JPanel jPanelAT2Poc7D1234 = new JPanel();
        JLabel jLabelAT2Poc7D1234 = new JLabel("4.20-ERequestPOCVersion:");
        JTextArea jTextAreaAT2Poc7D1234Value = new JTextArea(1, 16);
        jTextAreaAT2Poc7D1234Value.setFont(font);
        JButton jBtnAT2Poc7D1234 = new JButton("》");
        JButton jBtnPoc2AT7D1234 = new JButton("《");
        JTextArea jTextAreaAT2Poc7D1234Result = new JTextArea(1, 16);
        jTextAreaAT2Poc7D1234Result.setFont(font);
        jBtnAT2Poc7D1234.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaAT2Poc7D1234Value.getText().trim();
                String transResult = Transform.hex2String(transValue);
                jTextAreaAT2Poc7D1234Result.setText(transResult + "");
            }
        });
        jBtnPoc2AT7D1234.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaAT2Poc7D1234Result.getText().trim();
                String transResult = Transform.stringToHex(transValue);
                jTextAreaAT2Poc7D1234Value.setText(transResult + "00");
            }
        });
        jPanelAT2Poc7D1234.add(jLabelAT2Poc7D1234);
        jPanelAT2Poc7D1234.add(jTextAreaAT2Poc7D1234Value);
        jPanelAT2Poc7D1234.add(jBtnAT2Poc7D1234);
        jPanelAT2Poc7D1234.add(jBtnPoc2AT7D1234);
        jPanelAT2Poc7D1234.add(jTextAreaAT2Poc7D1234Result);
        return jPanelAT2Poc7D1234;
    }


    /**
     * 5.1 返回群组信息的指令AT转换成可读内容
     * 例子:AT+POC=7D1234
     * 指令  结果   操作ID   版本信息
     * 7D    00     1234     52323031393034313000
     * 备注:对当前使用的参数进行处理
     *
     * @param font
     * @return
     */
    private JPanel initPanelAT2Poc80011234(Font font) {
        JPanel jPanelAT2Poc80011234 = new JPanel();
        JLabel jLabelAT2Poc80011234 = new JLabel("5.1-ENotifyGroupListData:");
        JTextArea jTextAreaAT2Poc80011234Value = new JTextArea(1, 16);
        jTextAreaAT2Poc80011234Value.setFont(font);
        JButton jBtnAT2Poc80011234 = new JButton("》");
        JButton jBtnPoc2AT80011234 = new JButton("《");
        JTextArea jTextAreaAT2Poc80011234Result = new JTextArea(1, 16);
        jTextAreaAT2Poc80011234Result.setFont(font);
        jBtnAT2Poc80011234.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transValue = jTextAreaAT2Poc80011234Value.getText().trim();
                transValue = transValue.substring(0, transValue.length() - 4);
                String[] data = new String[transValue.length() / 2];
                for (int i = 0; i < data.length; i++) {
                    if (i % 2 == 0) {
                        data[i + 1] =  transValue.substring(i * 2, i * 2 + 2);
                    } else {
                        data[i - 1] = "\\u" +transValue.substring(i * 2, i * 2 + 2);
                    }
                }
                transValue = "";
                for (String unicode : data) {
                    transValue += unicode;
                }
                String transResult = Transform.unicode2String(transValue);
                jTextAreaAT2Poc80011234Result.setText(transResult + "");
            }
        });
        jBtnPoc2AT80011234.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        jPanelAT2Poc80011234.add(jLabelAT2Poc80011234);
        jPanelAT2Poc80011234.add(jTextAreaAT2Poc80011234Value);
        jPanelAT2Poc80011234.add(jBtnAT2Poc80011234);
        jPanelAT2Poc80011234.add(jBtnPoc2AT80011234);
        jPanelAT2Poc80011234.add(jTextAreaAT2Poc80011234Result);
        return jPanelAT2Poc80011234;
    }
}
