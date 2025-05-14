package com.geekily.geekilyArchiveAdmin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.geekily.geekilyArchiveAdmin.common.Util;
import com.geekily.geekilyArchiveAdmin.mapper.service.CommonService;

@Component
public class CommonDataInterceptor implements HandlerInterceptor {

	private final CommonService commonService;

    public CommonDataInterceptor(CommonService commonService) {
        this.commonService = commonService;
    }
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView != null) {
			modelAndView.addObject("isLogin"		, Util.isLogin(request));
			modelAndView.addObject("contextPath"	, request.getContextPath());
			modelAndView.addObject("subPath"		, Util.getSubPath(request));
			
			String[] subPathArray 	= Util.splitSubPath(Util.getSubPath(request));
			String currentMenuTitle	= "Home";
			if(subPathArray != null) {
				String path 		= subPathArray[subPathArray.length -1];
				currentMenuTitle 	= commonService.selectSelectedMenuTitle(path);
			}
			modelAndView.addObject("currentMenuTitle", currentMenuTitle);
        }
	}
}
