package com.utils;

/**
 * @Description: 进制转换:
 * @Auther: itsdf07
 * @Date: 2019/8/14/014 09:49
 */
public class Transform {
    public static void main(String[] args) {
        String values = "测";
        char[] chars = values.toCharArray();
        String unicodeOne = Integer.toString(chars[0], 16);
        System.out.println(unicodeOne);
    }

    /**
     * 10进制转16进制
     *
     * @param demical 十进制值
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
     * @param hex 十六进制值
     * @return
     */
    public static int hex2decimal(String hex) {
        return Integer.parseInt(hex, 16);
    }

    /**
     * 16进制转换成为string类型字符串
     *
     * @param hex 十六进制值
     * @return
     */
    public static String hex2String(String hex) {
        if (hex == null || hex.equals("")) {
            return null;
        }
        hex = hex.replace(" ", "");
        byte[] baKeyword = new byte[hex.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            hex = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return hex;
    }


    /**
     * 字符串转换为16进制字符串
     *
     * @param string
     * @return
     */
    public static String stringToHex(String string) {
        String hexString = "";
        for (int i = 0; i < string.length(); i++) {
            int ch = string.charAt(i);
            hexString = hexString + Integer.toHexString(ch);
        }
        return hexString;
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
        for (int i = 1; i < strs.length; i++) {
            returnStr.append((char) Integer.valueOf(strs[i], 16).intValue());
        }
        return returnStr.toString();
    }

    /**
     * 中文(字符串)转成unicode
     *
     * @param string 中文(字符串)
     * @return
     */
    public static String string2Unicode(String string) {
        char[] chars = string.toCharArray();
        StringBuilder unicode = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            String unicodeOne = Integer.toString(chars[i], 16);
            if (unicodeOne.length() <= 2) {
                unicodeOne = "00" + unicodeOne;
            }
            unicode.append("\\u" + unicodeOne);
        }
        return unicode.toString();
    }

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode2(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            String unicodeOne = Integer.toHexString(c);
            if (unicodeOne.length() <= 2) {
                unicodeOne = "00" + unicodeOne;
            }
            unicode.append("\\u" + unicodeOne);
        }
        return unicode.toString();
    }
}
