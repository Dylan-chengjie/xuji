package com.fb.xujimanage.util;

import org.apache.commons.codec.digest.DigestUtils;
import java.util.*;
import java.util.Map.Entry;


public class SignatureUtils {

    /**
     * 签名,MD5.
     *
     * @param paramMap 参数Map,不包含商户秘钥且顺序确定
     * @param key      商户秘钥
     * @return 签名串
     */
    public static String sign(Map<String, Object> paramMap, String key) {
        if (key == null) {
            throw new RuntimeException("key不能为空");
        }
        String sign = createSign(paramMap, key);
        return sign;
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     *
     * @param paramMap 参数Map,不包含商户秘钥且顺序确定
     * @param key      商户秘钥
     * @return 签名串
     */
    private static String createSign(Map<String, Object> paramMap, String key) {
        paramMap.put("timestamp",getSecondTimestamp(new Date())); //添加精确到秒，限制服务器访问
        StringBuffer sb = new StringBuffer();
        SortedMap<String, Object> sort = new TreeMap<String, Object>(paramMap);
        Set<Entry<String, Object>> es = sort.entrySet();
        Iterator<Entry<String, Object>> it = es.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"null".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        System.out.println("HMAC source:{" + sb.toString() + "}");
        // 签名
        String sign = DigestUtils.md5Hex(sb.toString()).toUpperCase();
        System.out.println("HMAC:{" + sign + "}");
        return sign;
    }

    /**
     * 验证签名, 仅支持MD5.
     *
     * @param paramMap 参数Map,不包含商户秘钥且顺序确定
     * @param key      商户秘钥
     * @param sign     签名串
     * @return 验签结果
     */
    public static boolean checkSign(Map<String, Object> paramMap, String key, String sign) {
        if (key == null) {
            throw new RuntimeException("key不能为空");
        }
        if (sign == null) {
            throw new RuntimeException("需要验签的字符为空");
        }

        return sign.equals(sign(paramMap, key));
    }


    /**
     * 获取精确到秒的时间戳
     * @return
     */
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }

}
