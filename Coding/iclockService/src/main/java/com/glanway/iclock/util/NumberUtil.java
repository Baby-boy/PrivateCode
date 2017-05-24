package com.glanway.iclock.util;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 数字工具类
 *
 * @author tianxuan
 */
public class NumberUtil {
    private final static int INT_MAX = 2147483647;
    private final static int INT_MIN = -2147483648;
    private final static int INT_MAX_LEN = 10;
    private final static String STR_0 = "0";
    private final static String STR_9 = "9";


    /**
     * 对数字进行零填充
     *
     * @param num    需要零填充的数字
     * @param length 总长度
     * @return
     */
    public static String zeroFill(Integer num, Integer length) {
        String rtVal = "";
        if (null != num || null != length) {
            String strNum = num.toString();
            int numLen = strNum.length();
            rtVal = getZero(length - numLen) + strNum;
        }
        return rtVal;
    }

    /**
     * 得到 number 个 0
     *
     * @param number
     * @return
     */
    public static String getZero(Integer number) {
        String rtVal = "";
        if (null != number && number > 0) {
            for (int i = 0; i < number; i++) {
                rtVal += STR_0;
            }
        }
        return rtVal;
    }

    /**
     * 将字符转为 整数
     * null 、空、非数字返回 0
     *
     * @return
     */
    public static Integer strToInteger(String str) {
        Integer rtVal = 0;
        if (null != str && !str.isEmpty()) {
            try {
                rtVal = Integer.parseInt(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rtVal;
    }


    /**
     * 得到N位整数随机数（不大于int的长度）
     *
     * @param len
     * @return
     */
    public static int getRandomInt(int len) {
        int minVal = getIntMinByLen(len);
        return new Random().nextInt(getIntMaxByLen(len) - minVal) + minVal;
    }

    /**
     * 得到N位最大正整数
     *
     * @param len
     * @return
     */
    public static int getIntMaxByLen(int len) {
        if (len > INT_MAX_LEN)   return INT_MAX;
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < len; i++) {
            str.append(STR_9);
        }
        return (str.length() > 0) ? Integer.parseInt(str.toString()) : 1;
    }

    /**
     * 得到N位最小整数
     *
     * @param len
     * @return
     */
    public static int getIntMinByLen(int len) {
        len = (len > INT_MAX_LEN) ? INT_MAX_LEN : len;
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < len - 1; i++) {
            str.append(STR_0);
        }
        return (str.length() > 0) ? Integer.parseInt(str.insert(0, "1").toString()) : 0;
    }

    /**
     * BigDecimal转为String
     * @param bigDecimal
     * @return
     */
    public static String bigDecimalToStr(BigDecimal bigDecimal){
        return (null == bigDecimal ? "" : bigDecimal.toString());
    }

    public static String doubleToStr(Double d){
        return (null == d ? "" : d.toString());
    }

}
