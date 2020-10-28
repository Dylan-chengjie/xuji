package com.fb.xujimanage.filter;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

@Component
@WebFilter(urlPatterns="/**",filterName="loginFilter")
public class LoginFilter implements Filter {

    private Logger logger = org.apache.logging.log4j.LogManager.getLogger(LoginFilter.class);

    //排除不拦截的url
    private static final String[] excludePathPatterns = { "/info/private"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        // 获取请求url地址，不拦截excludePathPatterns中的url
        String url = req.getRequestURI();
        if (Arrays.asList(excludePathPatterns).contains(url)) {
            //放行，相当于第一种方法中LoginInterceptor返回值为true
            chain.doFilter(request, response);
        }

        //1. 取出请求里面的所有头信息  ---- 得到一个枚举集合
       /* Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = req.getHeader(name);
            System.out.println("name="+name+"===value="+value);
        }*/

       /* String token = req.getHeader("token");

        TokenUtils tokenUtils = new TokenUtils(Constants.EXPIRE_TIME , Constants.TOKEN_SECRET);

        if(tokenUtils.verifyUserInfo(token)){
            logger.info("verifyUserInfo  is  success , do next !!! ");
            chain.doFilter(request, response);
        }else{
            logger.info("verifyUserInfo  is  fail ");
        }*/

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

}
