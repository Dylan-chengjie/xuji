package com.fb.xujimanage.util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.util.*;

/**
 * @author xiawei
 * @version 1.0
 * @date 2020/9/1 19:28
 * @description:用于调用第三方参数签名
 */
public class SignUtil {
    /**
     * 按字典序从小到大拼接字符串
     * @param param
     * @return
     */
    public static String createSign(Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        List<Integer> intP = new ArrayList<>();
        List<NameValuePair> params = new ArrayList<>();
        Set<String> set = param.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = it.next();
            params.add(new BasicNameValuePair(key, param.get(key)));
        }
        Collections.sort(intP, new Comparator<Integer>() {
            @Override
            public int compare(Integer arg0, Integer arg1) {
                return arg1.compareTo(arg0);
            }
        });
        Collections.sort(params, new Comparator<NameValuePair>() {
            @Override
            public int compare(NameValuePair arg0, NameValuePair arg1) {
                String name0 = arg0.getName();
                String name1 = arg1.getName();
                return name0.compareToIgnoreCase(name1);
            }
        });
        // 遍历排序的字典,并拼接"key=value"格式
        for (NameValuePair item : params) {
            String key = item.getName();
            String value =  item.getValue().trim();
            if (StringUtil.isNotBlank(value))
                sb.append("&").append(key).append("=").append(value);
        }
        String stringA = sb.toString().replaceFirst("&","");
        return stringA;
    }

    /**
     * 按字典序从小到大拼接字符串
     * @param paras
     * @return
     */
    public static String paySign(Map<String, String> paras){
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
        return sb.toString();
    }


    /**
     *
     * 方法用途: 对所有传入参数按照字段名的Unicode码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap   要排序的Map对象
     * @param urlEncode   是否需要URLENCODE
     * @param keyToLower    是否需要将Key转换为全小写
     *            true:key转化成小写，false:不转化
     * @return
     */
    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower)
    {
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try
        {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>()
            {

                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2)
                {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds)
            {
                if (StringUtils.isNotBlank(item.getKey()))
                {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode)
                    {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower)
                    {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else
                    {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false)
            {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e)
        {
            return null;
        }
        return buff;
    }
}
