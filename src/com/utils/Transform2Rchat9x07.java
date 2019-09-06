package com.utils;

/**
 * @Description: 针对Rchat 9x07 的AT转换
 * @Auther: itsdf07
 * @Date: 2019/8/13/013 18:16
 */
public class Transform2Rchat9x07 {

    /**
     * Rchat 9x07 模块AT指令中组名字转成中文
     *
     * @param unicode2Rchat9x07
     * @return
     */
    public static String unicode2Cn(String unicode2Rchat9x07) {
        return "";
    }

    /**
     * unicode转成中文(字符串)
     *
     * @param unicode Unicode码
     * @return
     */
    public static String unicode2String(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("\\\\u");
        StringBuilder returnStr = new StringBuilder();
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 0; i < strs.length; i++) {
            returnStr.append((char) Integer.valueOf(unicode.substring(i, i + 1), 16).intValue());
        }
        return returnStr.toString();
    }

    /**
     * 对Unicode内容进行换位处理:
     * 如“测试账号”，对应Unicode为:\u6d4b\u8bd5\u8d26\u53f7
     * 接收的数据源为(该数据无法直接转换，需要处理成上面数据格式):4b6dd58b268df753
     *
     * @param atUnicode Unicode码
     * @return
     */
    public static String Unicode2String(String atUnicode) {
        if (atUnicode == null || atUnicode.trim().length() % 2 != 0) {
            return "Unicode码格式不对";
        }
        String[] data = new String[atUnicode.length() / 2];
        for (int i = 0; i < data.length; i++) {
            if (i % 2 == 0) {
                data[i + 1] = atUnicode.substring(i * 2, i * 2 + 2);
            } else {
                data[i - 1] = "\\u" + atUnicode.substring(i * 2, i * 2 + 2);
            }
        }
        atUnicode = "";
        for (String unicode : data) {
            atUnicode += unicode;
        }
        String transResult = Transform.unicode2String(atUnicode);
        return transResult;
    }


}
