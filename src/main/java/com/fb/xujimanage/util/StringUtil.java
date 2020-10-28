package com.fb.xujimanage.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/8/13 09:06
 *判断字符串是否为null
 */
public class StringUtil {
    /**
     * <p>
     * Title: isBlank
     * </p>
     * <p>
     * Description: 判断是否为空 null、" "、"" 均返回true
     * </p>
     * @param str 要判断的字符
     * @return 空就为true
     */
    public static boolean isBlank(String str) {
        boolean result = true;

        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) != ' ') {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * <p>
     * Title: isNotBlank
     * </p>
     * <p>
     * Description: 判断是否非空
     * </p>
     *
     * @param str 要判断的字符
     * @return true表示非空
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * <p>
     * Title: isNumberString
     * </p>
     * <p>
     * Description: 如果str中的每一位都是数字，返回true，否则返回false
     * </p>
     *
     * @param str 需要判断的字符
     * @return 判断结果
     */
    public static boolean isNumberString(String str) {
        boolean result = true;
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 将异常信息转化成字符串
     * @param t
     * @return
     * @throws IOException
     */
    public static String exception2String(Throwable t) throws IOException {
        if(t == null)
            return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            t.printStackTrace(new PrintStream(baos));
        }finally{
            baos.close();
        }
        return baos.toString();
    }

    /**
     * 判断是否为BigDecimal
     */
    public static boolean isBigDecimalString(String str) {
        try {
            BigDecimal bigDecimal = new BigDecimal(str);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }

    }
}
