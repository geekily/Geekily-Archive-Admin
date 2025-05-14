package com.geekily.geekilyArchiveAdmin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geekily.geekilyArchiveAdmin.common.Constants;
import com.geekily.geekilyArchiveAdmin.common.Util;
import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveAdmin.mapper.service.LoginService;

@Controller
@RequestMapping(value = "login")
public class LoginController {
	
	@Resource
	LoginService loginService;

	@GetMapping(value = {"", "/"})
	public String login(HttpServletRequest request) throws Exception{
		if(Util.isLogin(request)) {
			return "redirect:/";
		}
		return "login/login";
	}
	
	@ResponseBody
	@PostMapping(value = "/check-email")
	public GeekilyMap checkEmail(@RequestBody GeekilyMap gMap) {
		try {
			if(loginService.checkEmail(gMap) != 1) {
				throw new Exception("This email isn't allowed to enter.");
			}
		} catch (Exception e) {
			gMap.setResultCode(0);
			gMap.setResultMessage(e.getMessage());
		}
		return gMap;
	}
	
	@ResponseBody
	@PostMapping(value = "/check-password")
	public GeekilyMap checkPassword(HttpSession session, @RequestBody GeekilyMap gMap) throws Exception{
		GeekilyMap userMap = null;
		try {
			userMap = loginService.selectUser(gMap);	
			if(Util.isEmpty(userMap)) {
				throw new Exception("The password is incorrect. Please try again.");
			}else {
				session.setAttribute("isSignedIn"	, true);
				session.setAttribute("signinType"	, Constants.SIGNIN_TYPE_NORMAL);
				session.setAttribute("userMap"		, userMap);
			}
		} catch (Exception e) {
			gMap.setResultCode(0);
			gMap.setResultMessage(e.getMessage());
		}
		return gMap;
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:/";
	}
}
