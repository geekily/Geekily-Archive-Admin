package com.geekive.geekiveArchiveAdmin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.geekive.geekiveArchiveAdmin.common.Constants;
import com.geekive.geekiveArchiveAdmin.common.JwtUtil;
import com.geekive.geekiveArchiveAdmin.common.Util;
import com.geekive.geekiveArchiveAdmin.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveAdmin.mapper.service.LoginService;
	
@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	private final LoginService loginService;
	private final JwtUtil jwtUtil;

    public LoginInterceptor(JwtUtil jwtUtil, LoginService loginService) {
        this.jwtUtil 		= jwtUtil;
        this.loginService 	= loginService;
    }
    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttributeNames().hasMoreElements()) {
			String signinType = (String) request.getSession().getAttribute("signinType");
			
			if(signinType.equals(Constants.SIGNIN_TYPE_NORMAL)) {
				return true;
			}
			if(signinType.equals(Constants.SIGNIN_TYPE_JWT)) {
				if(jwtUtil.hasToken(request)) {
					return true;
				}else {
					session.invalidate();
					return false;
				}
			}
		}else {
			if(jwtUtil.hasToken(request)) {
				if(this.jwtLogin(request)) {
					return true;
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}
	
	private Boolean jwtLogin(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String token 		= jwtUtil.getTokenFromCookie(request);

	    if(Util.isNotEmpty(token) && jwtUtil.isTokenValid(token)) {
	        GeekiveMap gMap = new GeekiveMap();
	        gMap.put("userUid", jwtUtil.extractValue(token));

	        GeekiveMap userMap = loginService.selectUser(gMap);
	        if(Util.isEmpty(userMap)) {
	            return false;
	        }

	        session.setAttribute("userMap"		, userMap);
	        session.setAttribute("isSignedIn"	, true);
	        session.setAttribute("signinType"	, Constants.SIGNIN_TYPE_JWT);
	        return true;
	    }
	    return false;
	}
}
