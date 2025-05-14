package com.geekily.geekilyArchiveAdmin.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.geekily.geekilyArchiveAdmin.common.JwtUtil;
import com.geekily.geekilyArchiveAdmin.common.PropertyUtil;
import com.geekily.geekilyArchiveAdmin.interceptor.CommonDataInterceptor;
import com.geekily.geekilyArchiveAdmin.interceptor.LoginInterceptor;
import com.geekily.geekilyArchiveAdmin.mapper.service.CommonService;
import com.geekily.geekilyArchiveAdmin.mapper.service.LoginService;

@Component
public class WebMvcConfig implements WebMvcConfigurer{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	private CommonService commonService;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor(jwtUtil, loginService))
			.order(1)
			.addPathPatterns("/**")
			.excludePathPatterns("/login/**", "/css/**", "/js/**", "/upload/**", "/error", "/image/**");
		
		registry.addInterceptor(new CommonDataInterceptor(commonService))
			.order(2)
			.addPathPatterns("/**")
			.excludePathPatterns("/option/**", "/menu/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/upload/**")
			.addResourceLocations("file:///" + PropertyUtil.getProperty("upload.path"));
	}
	
	
}
