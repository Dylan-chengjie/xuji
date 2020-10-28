package com.fb.xujimanage.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**@author  summer.chou
 * 参数签名类，为了安全密钥不能传递，（内含接口限制,同一条信息加密结果 10秒内有效，确保安全。服务器间时差不能大于10）
 */
public class Md5SignUtils {

    private static final Logger logger = LoggerFactory.getLogger(Md5SignUtils.class);

    /***
     *  字典升序（加密参数排序）
     * @param json 请求加密参数
     * @return  排序的字符串
     */
    public static String generatString(JSONObject json) {
        if (json.isEmpty()) {
            return "";
        }
        //限制同一条数据使用次数，设置有效时间精确到10秒
        // Long timestamp=getSecondTimestamp(new Date());
        //  json.put("timestamp",timestamp);
        Set<String> keySet = json.keySet();
        ArrayList<String> list = new ArrayList<>(keySet);
        StringBuffer sb = new StringBuffer();
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String key : list) {
            String value = json.getString(key);
            if (value != null) {
                if (!"sign".equals(key)) {
                    sb.append(key + "=" + value + "&");
                }
            }
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    /***
     * 加密
     * @param secret 密钥
     * @param json   请求参数
     * @return      签名字符串
     */
    public static String Md5Sign(String secret, JSONObject json) {
        String s = generatString(json);
        logger.info("排序后的字符串-->"+s);
        String preSign = s + "&appSecret=" + secret;
        return DigestUtils.md5Hex(preSign).toUpperCase();
    }

    /**
     * 验证签名, 仅支持MD5.
     *
     * @param json     请求去参数（不用排序）
     * @param key      商户秘钥
     * @param sign     签名字符串
     * @return 验签结果
     */
    public static boolean checkSign(JSONObject json, String key, String sign) {
        if (key == null) {
            throw new RuntimeException("key不能为空");
        }
        if (sign == null) {
            throw new RuntimeException("需要验签的字符sign为空");
        }

        return sign.equals(Md5Sign(key,json));
    }

    /**
     * 时间戳精确到截取后四位
     * @param date
     * @return
     */
    public static Long getSecondTimestamp(Date date){
        if (null == date) {
            return Long.valueOf(0);
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 6) {
            return Long.valueOf(timestamp.substring(0,length-6));
        } else {
           return Long.valueOf(0);
        }
    }
}
