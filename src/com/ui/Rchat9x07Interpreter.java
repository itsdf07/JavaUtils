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

    private ExecutorService service = Executors.newCachedThreadPool(new ThreadFactory() {

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "output");
        }
    });

    public static void main(String[] args) {
//        Rchat9x07Interpreter frame = new Rchat9x07Interpreter("9X07 AT 翻译器");
//        frame.showUI();
//        System.out.println(onTransactionAT("[15:53:34.710]发→◇AT+POC=00000001"));
//        System.out.println(onTransactionAT("[15:53:34.724]收←◆+POC:00000000"));
        System.out.println(onTransactionAT("[15:53:35.836]收←◆+POC:82020000000005f91dec3100300030003200310032003200300034000000"));

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
            ReadFromFile.readFileByLines(atFilePath, this);
        }
    }

    @Override
    public void readCallback(String content) {

        mJTATContent.setText(mJTATContent.getText() + "\n" + onTransactionAT(content));
//        service.submit(new Runnable() {
//
//            @Override
//            public void run() {
//                mJTATContent.append("\n" + content);
//            }
//        });

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
        ats[1] = doTransactionAT(ats[1]);
        at = "";
        for (String result : ats) {
            at += result;
        }
        return at;
    }

    private static String doTransactionAT(String at) {
        if ("ff00".equals(at)) {
            return "POC已经准备完成";
        } else if (at.startsWith("7D1234")) {
            return "AT:7D-查看POC版本";
        } else if (at.startsWith(AT_START_7D001234)) {
            return "AT:7D-" + Transform.hex2String(at.substring(AT_START_7D001234.length()));
        } else if (at.startsWith("00000001")) {
            return "AT:00-登陆 POC";
        } else if (at.startsWith("00000000")) {
            return "AT:00-登陆成功";
        } else if (at.startsWith("82")) {

            return "AT:82-" + "应用状态:" + at.substring(2, 4) + ",用户ID:" + Transform.hex2decimal(at.substring(4, 20) + ",用户名字:" + Transform2Rchat9x07.unicode2String(at.substring(20)));
        } else {
            return at;
        }

    }

}
