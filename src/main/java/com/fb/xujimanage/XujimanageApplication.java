package com.fb.xujimanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
//mapper 接口类扫描包配置
@MapperScan("com.fb.xujimanage.dao")
@EnableScheduling
@ServletComponentScan
public class XujimanageApplication extends WebMvcConfigurationSupport {

	/*@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX+"/static/");
		super.addResourceHandlers(registry);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(XujimanageApplication.class, args);
	}
}
