package com.fb.xujimanage.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fb.xujimanage.entity.AuthToken;
import com.fb.xujimanage.entity.Constants;
import com.fb.xujimanage.entity.vo.UserManageSysVo;
import com.fb.xujimanage.util.CommonResult;
import com.fb.xujimanage.util.RedisUtil;
import com.fb.xujimanage.util.TokenUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @program: xujimanage
 * @description: 鉴权的拦截器
 * @author: chengjie
 * @date: Created in 2020/8/25 19:20
 **/
@Log4j2
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private RedisUtil redisUtil;

    public AuthorizationInterceptor(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 如果打上了AuthToken注解则需要验证token
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {
            //从请求头中取出token
            String token = request.getHeader("token");

            log.info("Get token from request is {} ", token);
            if (token != null && token.length() != 0) {
                String userInfo = TokenUtils.getUserInfo(token);
                if (userInfo != null) {
                    UserManageSysVo manageSys = JSONObject.parseObject(userInfo, UserManageSysVo.class);
                    String userNum = manageSys.getUserNum();
                    String password = manageSys.getPassword();
                    String tokenKey = Constants.TOKEN_KEY + userNum + "_" + password;
                    if (redisUtil.hasKey(tokenKey)) {
                        redisUtil.expire(tokenKey, Constants.TOKEN_EXPIRES_TIME);
                        return true;
                    }
                }
            }
            PrintWriter out = null;
            try {
                response.setStatus(unauthorizedErrorCode);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                CommonResult commonResult = new CommonResult();
                commonResult.setStatus(response.getStatus());
                commonResult.setMsg(HttpStatus.UNAUTHORIZED.getReasonPhrase());
                out = response.getWriter();
                out.println(JSON.toJSON(commonResult));

                return false;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != out) {
                    out.flush();
                    out.close();
                }
            }
        }
        return true;
    }
}
