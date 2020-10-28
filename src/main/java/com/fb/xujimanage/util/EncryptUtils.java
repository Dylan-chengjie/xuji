package com.fb.xujimanage.util;

import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

/**
 * @ClassName EncryptUtils.java
 * @Description 各种加密算法工具类
 * @Author Joy.zhou
 * @Date 2019/9/2
 * @Version V1.0
**/
public class EncryptUtils {

    public static String encrypt2Md5(String param){
        return DigestUtils.md5Hex(param);
    }

    public static byte[] hmac256(byte[] key, String msg) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
        mac.init(secretKeySpec);
        return mac.doFinal(msg.getBytes("utf-8"));
    }

    public static String sha256Hex(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes("utf-8"));
        return DatatypeConverter.printHexBinary(d).toLowerCase();
    }



    public static void main(String[] args) throws Exception{
//        String payload = "{\"Limit\": 1, \"Filters\": [{\"Values\": [\"\\u672a\\u547d\\u540d\"], \"Name\": \"instance-name\"}]}";
//        String payload2 = "{\"Limit\": 1, \"Filters\": [{\"Name\": \"instance-name\",\"Values\": [\"\\u672a\\u547d\\u540d\"]}]}";
//        String hashedRequestPayload = sha256Hex(payload);
//        String hashedRequestPayload2 = sha256Hex(payload2);
//        System.out.println(hashedRequestPayload);
//        System.out.println(hashedRequestPayload2);
    	String passWold=encrypt2Md5("0003493");
    	System.out.println(passWold);
    	String passWold123456=encrypt2Md5("123456");
       	System.out.println(passWold123456);
    }
}
