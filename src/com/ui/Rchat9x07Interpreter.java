package com.ui;

import com.utils.ReadFromFile;
import com.utils.Transform;
import com.utils.Transform2Rchat9x07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @Description: 9X07 AT 指令翻译器
 * @Auther: itsdf07
 * @Date: 2019/8/20/020 14:27
 */
public class Rchat9x07Interpreter extends BaseJFrame implements ActionListener, ReadFromFile.IReadCallback {
    public static final String AT_START_7D001234 = "7D001234";//4.20 ERequestPOCVersion 查看POC版本
    private final static String ACTION_OPENATFILE = "ATFILE";
    JButton mJBOpenATFile = new JButton("打开AT日志文件");
    JLabel mJLATFilePath = new JLabel("AT日志路径:");
    JTextArea mJTATContent = new JTextArea();


    public static void main(String[] args) {
        Rchat9x07Interpreter frame = new Rchat9x07Interpreter("9X07 AT 翻译器");
        frame.showUI();
//        System.out.println(Transform.hex2decimal("01"));
//        System.out.println(onTransactionAT("[15:18:41.357]收←◆+POC:8202000000f7f2069e573800380030006153f75337003700300031000000"));
//        System.out.println(onTransactionAT("[15:53:34.724]收←◆+POC:0200123469703d302e302e302e303b69643d74657374313b00"));
//        System.out.println(onTransactionAT("[16:18:17.633]收←◆+POC:82020000000005f91dec3100300030003200310032003200300034000000"));

    }

    public Rchat9x07Interpreter(String title) {
        super(title);
        setBounds(x, y, 600, 700);
    }

    public void showUI() {
        this.setLayout(new BorderLayout(10, 5));
        Font font = new Font("黑体", Font.PLAIN, 20);//设置字体
        JPanel jPTitleLayout = new JPanel();
        jPTitleLayout.setLayout(new FlowLayout(FlowLayout.LEFT));
        mJBOpenATFile.setActionCommand(ACTION_OPENATFILE);
        mJBOpenATFile.addActionListener(this);
        jPTitleLayout.add(mJBOpenATFile);
        jPTitleLayout.add(mJLATFilePath);

        JScrollPane jSPATContent = new JScrollPane();
        jSPATContent.setViewportView(mJTATContent);

        add(jPTitleLayout, BorderLayout.NORTH);
        add(jSPATContent, BorderLayout.WEST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);//窗体在屏幕上居中显示
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ACTION_OPENATFILE)) {
            JFileChooser jf = new JFileChooser();
            jf.showOpenDialog(this);//显示打开的文件对话框
            File file = jf.getSelectedFile();//使用文件类获取选择器选择的文件
            String atFilePath = file.getAbsolutePath();//返回路径名
            mJLATFilePath.setText("AT日志路径:" + atFilePath);
            mJTATContent.setText("");
            ReadFromFile.readFileByLines(atFilePath, this);
        }
    }

    @Override
    public void readCallback(String content) {
        mJTATContent.setText(mJTATContent.getText() + "\n" + onTransactionAT(content));
    }

    private static String onTransactionAT(String at) {
        int atType = 0;//AT类型：0-发送，1-接收
        at = at.toUpperCase();
        String[] ats = null;
        if (at.indexOf("+POC:") != -1) {
            atType = 1;
            ats = at.split("\\+POC:");
            ats[0] += "+POC:";
        } else if (at.indexOf("AT+POC=") != -1) {
            atType = 0;
            ats = at.split("AT\\+POC=");
            ats[0] += "AT+POC=";
        }
        if (null == ats) {
            return at;
        }
        ats[1] = doTransactionAT(ats[1], atType);
        at = "";
        for (String result : ats) {
            at += result;
        }
        return at;
    }

    /**
     * @param at     AT指令
     * @param atType AT类型：0-发送指令（AT+POC=），1=接收指令（+POC:）
     * @return
     */
    private static String doTransactionAT(String at, int atType) {
        at = at.toUpperCase();
        if ("FF00".equals(at)) {
            return "POC已经准备完成，您可以开始发送POC的AT指令了！";
        } else if (at.startsWith("00")) {
            if (atType == 0) {//AT+POC=00000001
                int ttsType = (int) Transform.hex2decimal(at.substring(6, 8));
                String ttsInfo = "TTS状态未知";
                if (ttsType == 0) {
                    ttsInfo = "TTS关闭";
                } else if (ttsType == 1) {
                    ttsInfo = "TTS打开";
                } else {
                    return "AT+POC=00-您正在登陆POC,TTS状态未知:" + ttsType;
                }
                return "AT+POC=00-您正在登陆POC," + ttsInfo;
            } else {//+POC:00000000
                int loginStatus = (int) Transform.hex2decimal(at.substring(2, 4));
                return "+POC=00-登陆结果:" + loginStatus;
            }
        } else if (at.startsWith("01")) {
            return "AT:01-" + at;
        } else if (at.startsWith("02")) {
            if (atType == 0) {
                return "AT+POC=02-您正在请求POC的参数信息";
            } else {
                return "+POC=02-请求结果:" + at.substring(2, 4) + ",当前使用的参数:" + Transform.hex2String(at.substring(8));
            }
        } else if (at.startsWith("04")) {
            if (atType == 0) {
                if ("041234".equals(at)) {
                    return "AT+POC=04-您注销了POC应用";
                } else {
                    return "AT+POC=04-发起的指令格式不对:" + at;
                }
            } else {
                return "+POC=04-注销结果:" + at.substring(2, 4);
            }
        } else if (at.startsWith("09")) {
            if (atType == 0) {
                return "AT+POC=09-您正在进入群组“" + Transform.hex2decimal(at.substring(6)) + "”";
            } else {
                return "+POC=09-进入结果:" + at.substring(2, 4);
            }
        } else if (at.startsWith("0A")) {
            //TODO 待验证
            if (atType == 0) {
                String userIds = "";
                int start = 6;
                for (int i = 0; i < (at.substring(6).length() / 16); i++) {
                    userIds += Transform.hex2decimal(at.substring(start, start + 16)) + ",";
                    start += 16;
                }
                return "AT+POC=0A-单呼用户:" + userIds;
            } else {
                return "+POC=0A-单呼结果:" + at.substring(2, 4);
            }
        } else if (at.startsWith("0B")) {
            if (atType == 0) {
                return "AT+POC=0B-您正在按下PTT键";
            } else {
                return "+POC=0B-PTT按下结果:" + at.substring(2, 4);
            }
        } else if (at.startsWith("0C")) {
            if (atType == 0) {
                return "AT+POC=0C-您正在放开PTT键";
            } else {
                return "+POC=0C-PTT放开结果:" + at.substring(2, 4);
            }
        } else if (at.startsWith("0D")) {
            if (atType == 0) {
                return "AT+POC=0D-您正在获取POC的频道组信息";
            } else {
                return "+POC=0D-频道数获取结果:" + at.substring(2, 4) + ",频道数=" + Transform.hex2decimal(at.substring(8));
            }
        } else if (at.startsWith("11")) {
            if (atType == 0) {
                int locType = (int) Transform.hex2decimal(at.substring(6, 8));
                if (locType == 0) {
                    return "AT+POC=11-您正在上报GPS位置为:" + Transform.hex2String(at.substring(8));
                } else if (locType == 1) {
                    return "AT+POC=11-您正在上报LBS位置为:" + Transform.hex2String(at.substring(8));
                } else {
                    return "AT+POC=11-您正在上报未知类型的位置为:" + Transform.hex2String(at.substring(8));
                }
            } else {
                return "+POC=0C-位置上报结果:" + at.substring(2, 4);
            }
        } else if (at.startsWith("13")) {
            if (atType == 0) {
                return "AT+POC=13-您正在获取频道“" + Transform.hex2decimal(at.substring(6)) + "”的在线成员个数";
            } else {
                return "+POC=13-获取结果:" + at.substring(2, 4) + ",在线成员数量=" + Transform.hex2decimal(at.substring(8));
            }
        } else if (at.startsWith("24")) {
            if (atType == 0) {
                String ttsType = at.substring(6, 8);
                if ("01".equals(ttsType)) {
                    return "AT+POC=24-您正在打开TTS播放功能";
                } else {
                    return "AT+POC=24-您正在关闭TTS播放功能";
                }
            } else {
                return "+POC=24-TTS设置结果:" + at.substring(2, 4);
            }
        } else if (at.startsWith("28")) {
            if (atType == 0) {
                return "AT+POC=28-您正在获取北京时间";
            } else {
                String transValue = at.substring(8);
                if ((transValue.length() % 2) != 0 && (transValue.length() / 2) < 7) {
                    return "+POC=24-时间数据异常:" + transValue;
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
                transValue = year + "年" + month + "月" + day + "日 " + time + ":" + minute + ":" + second + " 周" + week;
                return "+POC=28-当前北京时间:" + transValue;
            }
        } else if (at.startsWith("7A")) {
            if (atType == 0) {
                String time = at.substring(6, 8);
                return "AT+POC=7A-您正在设置心跳包频率为:" + Transform.hex2decimal(time);
            } else {
                return "+POC=7A-心跳包间隔设置结果:" + at.substring(2, 4);
            }
        } else if (at.startsWith("7C")) {
            if (atType == 0) {
                String toneType = at.substring(6, 8);
                if ("01".equals(toneType)) {
                    return "AT+POC=7C-您正在开启Tone音功能";
                } else {
                    return "AT+POC=7C-您正在关闭Tone音功能";
                }
            } else {
                return "+POC=24-Tone音设置结果:" + at.substring(2, 4);
            }
        } else if (at.startsWith("7D")) {
            if (atType == 0) {
                return "AT+POC=7D-您正在查看POC版本信息";
            } else {
                return "+POC=7D-POC版本信息为:" + Transform.hex2String(at.substring(AT_START_7D001234.length()));
            }
        } else if (at.startsWith("80")) {
            String flag = at.substring(2, 4);
            if ("01".equals(flag)) {
                return "+POC=80-当前频道,网络顺序为:" + Transform.hex2decimal(at.substring(8, 12)) + ",频道ID为:" + Transform.hex2decimal(at.substring(12, 20))
                        + ",成员数:" + Transform.hex2decimal(at.substring(20, 24))
                        + ",频道名:" + Transform2Rchat9x07.Unicode2String(at.substring(24, at.length() - 4));
            } else {
                return "+POC=80-非当前频道,网络顺序为:" + Transform.hex2decimal(at.substring(8, 12)) + ",频道ID为:" + Transform.hex2decimal(at.substring(12, 20))
                        + ",成员数:" + Transform.hex2decimal(at.substring(20, 24))
                        + ",频道名:" + Transform2Rchat9x07.Unicode2String(at.substring(24, at.length() - 4));
            }

        } else if (at.startsWith("81")) {
            String ipocStatus = at.substring(2, 4);//成员状态
            String ipocId;//用户ID
            String netOrder;//网络顺序
            String ipocDisplayName;//用户ID
            if ("01".equals(ipocStatus)) {
                netOrder = Transform.hex2decimal(at.substring(8, 12)) + "";
                ipocId = Transform.hex2decimal(at.substring(12, 28)) + "";
                ipocDisplayName = Transform2Rchat9x07.Unicode2String(at.substring(28, at.length() - 4));
                return "+POC=81-" + "成员状态(离线):" + ipocStatus + ",网络顺序:" + netOrder + ",用户ID:" + ipocId + ",成员名称:" + ipocDisplayName;
            } else if ("02".equals(ipocStatus)) {
                netOrder = Transform.hex2decimal(at.substring(8, 12)) + "";
                ipocId = Transform.hex2decimal(at.substring(12, 28)) + "";
                ipocDisplayName = Transform2Rchat9x07.Unicode2String(at.substring(28, at.length() - 4));
                return "+POC=81-" + "成员状态(在线未在频道内):" + ipocStatus + ",网络顺序:" + netOrder + ",用户ID:" + ipocId + ",成员名称:" + ipocDisplayName;
            } else if ("03".equals(ipocStatus)) {
                netOrder = Transform.hex2decimal(at.substring(8, 12)) + "";
                ipocId = Transform.hex2decimal(at.substring(12, 28)) + "";
                ipocDisplayName = Transform2Rchat9x07.Unicode2String(at.substring(28, at.length() - 4));
                return "+POC=81-" + "成员状态(在线且在频道内):" + ipocStatus + ",网络顺序:" + netOrder + ",用户ID:" + ipocId + ",成员名称:" + ipocDisplayName;
            } else {
                return "+POC=81-解析异常:" + at;
            }
        } else if (at.startsWith("82")) {
            String ipocStatus = at.substring(2, 4);//应用状态
            String ipocId;//用户ID
            if ("00".equals(ipocStatus)) {
                ipocId = Transform.hex2decimal(at.substring(4, 20)) + "";//用户ID
                return "+POC=82-" + "应用状态(离线):" + ipocStatus + ",用户ID:" + ipocId + ",离线原因:" + Transform2Rchat9x07.Unicode2String(at.substring(20, at.length() - 4));
            } else if ("01".equals(ipocStatus)) {
                return "+POC=82-" + "应用状态(登陆中)" + ipocStatus;
            } else if ("02".equals(ipocStatus)) {
                ipocId = Transform.hex2decimal(at.substring(4, 20)) + "";//用户ID
                return "+POC=82-" + "应用状态(登陆成功):" + ipocStatus + ",用户ID:" + ipocId + ",用户昵称:" + Transform2Rchat9x07.Unicode2String(at.substring(20, at.length() - 4));
            } else if ("03".equals(ipocStatus)) {
                return "+POC=82-" + "应用状态(注销中):" + ipocStatus;
            } else {
                return "+POC=82-解析异常:" + at;
            }
        } else if (at.startsWith("83")) {
            String pttStatus = at.substring(2, 4);//讲话状态
            String ipocID;//讲话者账号ID
            String ipocDisplyName = Transform2Rchat9x07.Unicode2String(at.substring(20, at.length() - 4));//讲话者账号昵称
            if ("00".equals(pttStatus)) {
                ipocID = Transform.hex2decimal(at.substring(4, 20)) + "";//讲话者账号ID
                return "+POC=83-" + "讲话状态(正在讲话):" + at.substring(2, 4) + ",讲话用户ID:" + ipocID + ",讲话用户名字:" + ipocDisplyName;
            } else if ("01".equals(pttStatus)) {
                ipocID = at.substring(4, 20);//讲话者账号ID
                if ("ffffffffffffffff".equals(at.substring(4, 20).toLowerCase())) {
                    return "+POC=83-" + "讲话状态(话权空闲):" + at.substring(2, 4) + ",讲话用户ID:" + ipocID + ",讲话用户名字:" + ipocDisplyName;
                } else {
                    ipocID = Transform.hex2decimal(at.substring(4, 20)) + "";//讲话者账号ID
                    return "+POC=83-" + "讲话状态(可抢夺):" + at.substring(2, 4) + ",讲话用户ID:" + ipocID + ",讲话用户名字:" + ipocDisplyName;
                }
            } else {
                return "+POC=83-解析异常:" + at;
            }
        } else if (at.startsWith("84")) {
            return "+POC=84-:" + Transform2Rchat9x07.Unicode2String(at.substring(4, at.length() - 4));
        } else if (at.startsWith("85")) {
            long notifyType = Transform.hex2decimal(at.substring(2, 4));
            if (notifyType == 0) {
                return "+POC=85-通知类型:" + notifyType + ",群组信息有变化，需要更新";
            } else if (notifyType == 1) {
                return "+POC=85-通知类型:" + notifyType + ",成员的信息更新";
            } else {
                return "+POC=85-解析异常:" + at;
            }
        } else if (at.startsWith("86")) {
            String roomType = Transform.hex2decimal(at.substring(2, 4)) + "";
            if ("01".equals(roomType)) {
                return "+POC=86-进入临时频道(单呼): " + roomType + ",频道ID:" + Transform.hex2decimal(at.substring(4, 12)) + ",频道名:" + Transform2Rchat9x07.Unicode2String(at.substring(12, at.length() - 4));
            } else {
                return "+POC=86-进入频道:" + roomType + ",频道ID:" + Transform.hex2decimal(at.substring(4, 12)) + ",频道名:" + Transform2Rchat9x07.Unicode2String(at.substring(12, at.length() - 4));
            }
        } else {
            return at;
        }
    }


}
