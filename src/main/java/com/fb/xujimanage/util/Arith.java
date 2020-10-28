package com.fb.xujimanage.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * Title:Arith
 * Description: 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精
 * 确的浮点数运算，包括加减乘除和四舍五入。
 */
public class Arith {

    /** 默认除法运算精度 */
    public static final int DEF_DIV_SCALE = 10;

    /**
     * <p>
     * Title: Arith
     * </p>
     * <p>
     * Description: 这个类不能实例化
     * </p>
     */
    private Arith() {
    }

    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static BigDecimal add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return add(b1,b2);
    }
    public static  BigDecimal add(String v1,String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return add(b1,b2);
    }
    public static  BigDecimal add(String v1,String v2,int scale){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return add(b1,b2,scale);
    }
    public static  BigDecimal add(BigDecimal v1,String v2){
        BigDecimal b2 = new BigDecimal(v2);
        return add(v1,b2);
    }
    public static  BigDecimal add(BigDecimal v1,String v2,int scale){
        BigDecimal b2 = new BigDecimal(v2);
        return add(v1,b2,scale);
    }

    public static  BigDecimal add(String v1,BigDecimal v2){
        BigDecimal b1 = new BigDecimal(v1);
        return add(b1,v2);
    }
    public static  BigDecimal add(String v1,BigDecimal v2,int scale){
        BigDecimal b1 = new BigDecimal(v1);
        return add(b1,v2,scale);
    }

    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        return v1.add(v2);
    }

    public static BigDecimal add(BigDecimal v1, BigDecimal v2,int scale){
        return round(v1.add(v2),scale);
    }

    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return sub(b1,b2);
    }
    public static BigDecimal sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return sub(b1,b2);
    }
    public static BigDecimal sub(String v1, String v2,int scale) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return sub(b1,b2,scale);
    }

    public static BigDecimal sub(BigDecimal v1, String v2) {
        BigDecimal b2 = new BigDecimal(v2);
        return sub(v1,b2);
    }
    public static BigDecimal sub(BigDecimal v1, String v2,int scale) {
        BigDecimal b2 = new BigDecimal(v2);
        return sub(v1,b2,scale);
    }

    public static BigDecimal sub(String v1, BigDecimal v2) {
        BigDecimal b1 = new BigDecimal(v1);
        return sub(b1,v2);
    }
    public static BigDecimal sub(String v1, BigDecimal v2,int scale) {
        BigDecimal b1 = new BigDecimal(v1);
        return sub(b1,v2,scale);
    }

    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
        return v1.subtract(v2);
    }

    public static BigDecimal sub(BigDecimal v1, BigDecimal v2 ,int scale) {
        return round(v1.subtract(v2),scale);
    }

    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return mul(b1,b2);
    }
    public static BigDecimal mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return mul(b1,b2);
    }
    public static BigDecimal mul(String v1, String v2,int scale) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return mul(b1,b2,scale);
    }

    public static BigDecimal mul(BigDecimal v1, String v2) {
        BigDecimal b2 = new BigDecimal(v2);
        return mul(v1,b2);
    }
    public static BigDecimal mul(BigDecimal v1, String v2,int scale) {
        BigDecimal b2 = new BigDecimal(v2);
        return mul(v1,b2,scale);
    }

    public static BigDecimal mul(String v1, BigDecimal v2) {
        BigDecimal b1 = new BigDecimal(v1);
        return mul(b1,v2);
    }
    public static BigDecimal mul(String v1, BigDecimal v2,int scale) {
        BigDecimal b1 = new BigDecimal(v1);
        return mul(b1,v2,scale);
    }

    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        return v1.multiply(v2);
    }

    public static BigDecimal mul(BigDecimal v1, BigDecimal v2,int scale) {
        return round(v1.multiply(v2),scale);
    }

    /**
     * @param v1
     * @param v2
     * @param v3
     * @param scale
     * @return
     */
    public static BigDecimal mul(BigDecimal v1,BigDecimal v2,BigDecimal v3,int scale){
      return mul(mul(v1,v2),v3,scale);
    }


    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static BigDecimal div(double v1, double v2) {

        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static BigDecimal div(BigDecimal v1, BigDecimal v2){

        return div(v1, v2, DEF_DIV_SCALE);
    }
    public static BigDecimal div(String v1, String v2){

        return div(v1, v2, DEF_DIV_SCALE);
    }
    public static BigDecimal div(String v1, BigDecimal v2){

        return div(v1, v2, DEF_DIV_SCALE);
    }
    public static BigDecimal div(BigDecimal v1, String v2){

        return div(v1, v2, DEF_DIV_SCALE);
    }


    public static BigDecimal div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("要保留的小数位数必须是一个正整数或者0");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return div(b1, b2, scale);
    }

    public static BigDecimal div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("要保留的小数位数必须是一个正整数或者0");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return div(b1, b2, scale);
    }

    public static BigDecimal div(BigDecimal v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("要保留的小数位数必须是一个正整数或者0");
        }
        BigDecimal b2 = new BigDecimal(v2);
        return div(v1, b2, scale);
    }

    public static BigDecimal div(String v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("要保留的小数位数必须是一个正整数或者0");
        }
        BigDecimal b1 = new BigDecimal(v1);
        return div(b1, v2, scale);
    }

    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("要保留的小数位数必须是一个正整数或者0");
        }
        return v1.divide(v2, scale, RoundingMode.HALF_UP);
    }

    public static void main(String[] args){

        BigDecimal one = new BigDecimal("3");
        BigDecimal two = new BigDecimal("4");

        System.out.println(div(one,two,1));

    }




    /**
     * <p>
     * Title: muchmul
     * </p>
     * <p>
     * Description: 精确计算一个数字的N次方
     * </p>
     * @param b1 底数
     * @param count 指数
     * @return 底数的指数次方
     */
    public static BigDecimal muchmul(BigDecimal b1, int count) {
        BigDecimal b2 = new BigDecimal(Double.toString(1.00));
        if (count == 0) {
            return b2;
        } else if (count == 1) {
            return b1;
        } else if (count > 1) {

            for (int i = 0; i < count; i++) {
                b2 = b2.multiply(b1);
            }
            return b2;
        }

        return b1;

    }

    /**
     * 提供精确的小数位四舍五入处理。
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static BigDecimal round(Double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("要保留的小数位数必须是一个正整数或者0");
        }
        BigDecimal b = new BigDecimal(v);
        return round(b,scale);
    }

    public static BigDecimal round(BigDecimal v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("要保留的小数位数必须是一个正整数或者0");
        }
        BigDecimal one = new BigDecimal("1");
        return v.divide(one, scale, RoundingMode.HALF_UP);
    }

    /**
     * 类型转换
     * @param v
     * @return
    */
    public static Double toDouble(BigDecimal v){
        return Double.valueOf(v.toString());
    }
    public static Integer toInteger(BigDecimal v){
        return Integer.valueOf(v.toString());
    }

    public static BigDecimal toBigDecimal(Double v){
        return new BigDecimal(Double.toString(v));
    }

    public static BigDecimal toBigDecimal(Integer v){return new BigDecimal(Integer.toString(v));}

    public static BigDecimal toBigDecimal(Long v){return new BigDecimal(Long.toString(v));}

    public static BigDecimal toBigDecimal(String v){return new BigDecimal(v);}


    /**
     *  比较大小写
     *  返回值如果是 -1 代表前面小于后面的数  0 代表等于  1 代表前面大于后面的数
     */

    public static int compareTo(BigDecimal v1, BigDecimal v2){
        return v1.compareTo(v2);
    }

    public static int compareTo(BigDecimal v1, String v2){
        BigDecimal b2 = new BigDecimal(v2);
        return compareTo(v1,b2);
    }

    public static int compareTo(String v1, BigDecimal v2){
        BigDecimal b1 = new BigDecimal(v1);
        return compareTo(b1,v2);
    }

    public static int compareTo(String v1, String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return compareTo(b1,b2);
    }

}