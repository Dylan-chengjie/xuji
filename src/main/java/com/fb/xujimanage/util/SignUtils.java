package com.fb.xujimanage.util;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SignUtils {

    /**
     * 支付签名
     * @param timestamp
     * @param noncestr
     * @param packages
     * @return
     * @throws
     */
    public static String paySign(String timestamp, String noncestr,String packages,String appId){
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("appid", appId);
        paras.put("timestamp", timestamp);
        paras.put("noncestr", noncestr);
        paras.put("package", packages);
        paras.put("signType", "MD5");
        StringBuffer sb = new StringBuffer();
        Set es = paras.entrySet();//字典序
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            //为空不参与签名、参数名区分大小写
            if (null != v && !"".equals(v) && !"sign".equals(k)  && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        String sign = MD5Util.MD5(sb.toString()).toUpperCase();
        return sign;
    }

}
