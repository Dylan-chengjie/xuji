package com.fb.xujimanage.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program: xujimanage
 * @description: 鉴权配置
 * @author: chengjie
 * @date: Created in 2020/8/24 20:54
 **/
@Configuration
public class AuthorizationConfigurer extends WebMvcConfigurationSupport {



    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    public void setAuthorizationInterceptor(AuthorizationInterceptor authorizationInterceptor) {
        this.authorizationInterceptor = authorizationInterceptor;
    }

    /**
     * 注入自定义拦截器
     *
     * @param registry
     * @Title: addInterceptors
     * @Description: 先add的拦截器会越靠外，即越靠近浏览器
     * @Date 2020年8月25日 下午2:47:28
     * @author chengjie
     */


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

}
