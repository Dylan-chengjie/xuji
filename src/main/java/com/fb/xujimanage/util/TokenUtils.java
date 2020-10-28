package com.fb.xujimanage.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fb.xujimanage.entity.vo.UserManageSysVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author zm
 * @desc 使用token验证用户是否登录
 **/

public class TokenUtils {

    static Logger logger = Logger.getLogger("TokenUtils");
    //设置过期时间
    private static long EXPIRE_TIME = 24 * 60 * 60 * 100000;
    //token秘钥
    private static final String TOKEN_SECRET = "ZCfasf2020huajgFSH8ufguGuwuBQW24E";

//    //设置过期时间
//    private long EXPIRE_TIME ;
//    //token秘钥
//    private String TOKEN_SECRET ;

//    public TokenUtils(long EXPIRE_TIME, String TOKEN_SECRET) {
//        this.EXPIRE_TIME = EXPIRE_TIME;
//        this.TOKEN_SECRET = TOKEN_SECRET;
//    }

    public static String createToken(String userInfo) {
        String token = "";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("userInfo", userInfo)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }


    /**
     * @desc 验证token，通过返回true
     * @params [token]需要校验的串
     **/
    public static boolean verifyUserInfo(String token, String name, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String userName = jwt.getClaim("username").asString();
            String passWord = jwt.getClaim("password").asString();

            // 过期时间由生成方指定，校验方无法修改
            Date expireDate = jwt.getExpiresAt();
            expireDate.getTime();
            logger.info("the expireDate is " + expireDate.toString());

//            logger.info("userName is : "+userName);
//            logger.info("password is : " + passWord);


            if (!name.equals(userName)) {
                logger.info("the  username is  wrong : " + userName);
                return false;
            }

            if (!password.equals(passWord)) {
                logger.info("the  passWord is  wrong : " + passWord);
                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @desc 获取用户信息
     * @params [token]需要校验的串
     **/
    public static String getUserInfo(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String userInfo = jwt.getClaim("userInfo").asString();

            logger.info("userInfo is : " + userInfo);

            return userInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @desc 获取用户信息
     * @params [token]需要校验的串
     **/
    public static String getUserNum() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader("token");
        if (StringUtils.isNotEmpty(token)) {
            String userInfo = getUserInfo(token);
            UserManageSysVo manageSys = JSONObject.parseObject(userInfo, UserManageSysVo.class);
            return null != manageSys ? manageSys.getUserNum() : null;
        }
        return null;
    }

//    public static void main(String[] args) {
//        String username = "zhangsan";
//        String password = "123";
//        String token0 = TokenUtils.createToken(username, password);
//        System.out.println(token0);
//
//        String username1 = "zhangsan";
//        String password1 = "1234";
//        String token1 = TokenUtils.createToken(username, password);
//        System.out.println(token1);

    //boolean b = tokenUtils.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMyIsImV4cCI6MTU5MzI2MTQ0NiwidXNlcm5hbWUiOiJ6aGFuZ3NhbiJ9.JEybRyf_zGqZWaAAKcJ0LhicZ2dBQTDYa8Qx4ajpPPQ");

//        boolean b = TokenUtils.verify(token1);
//        System.out.println(b);
//    }
}
