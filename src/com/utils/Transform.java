package com.utils;

/**
 * @Description: 进制转换:
 * @Auther: itsdf07
 * @Date: 2019/8/14/014 09:49
 */
public class Transform {
    /**
     * 10进制转16进制
     *
     * @param demical
     * @return
     */
    public static String demical2Hex(int demical) {
        if (!(demical + "").matches("[0-9]*")) {
            return "";
        }
        String hexadecimal = Integer.toHexString(demical);
        return hexadecimal;
    }

    /**
     * 16进制转10进制
     *
     * @param hex
     * @return
     */
    public static int hex2decimal(String hex) {
        return Integer.parseInt(hex, 16);
    }


    /**
     * unicode转成中文
     *
     * @param unicode Unicode码
     * @return
     */
    public static String unicode2Cn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("\\\\u");
        StringBuilder returnStr = new StringBuilder();
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr.append((char) Integer.valueOf(strs[i], 16).intValue());
        }
        return returnStr.toString();
    }

    /**
     * 中文转成unicode
     *
     * @param cn
     * @return
     */
    public static String cn2Unicode(String cn) {
        char[] chars = cn.toCharArray();
        StringBuilder returnStr = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            returnStr.append("\\u" + Integer.toString(chars[i], 16));
        }
        return returnStr.toString();
    }
}
