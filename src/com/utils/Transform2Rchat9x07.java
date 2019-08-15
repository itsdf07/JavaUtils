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
}
