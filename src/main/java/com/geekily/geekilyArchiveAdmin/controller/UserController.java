package com.geekily.geekilyArchiveAdmin.controller;

import java.util.Arrays;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.geekily.geekilyArchiveAdmin.common.Constants;
import com.geekily.geekilyArchiveAdmin.common.Util;
import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyConnector;
import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveAdmin.mapper.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Resource
	UserService userService;
	
	@GetMapping(value = "/profile")
	public String progile(HttpServletRequest request, ModelMap modelMap) throws Exception{
		GeekilyMap userMap = userService.selectUser(new GeekilyMap());
		modelMap.put("userMap", userMap);
		return "user/profile/profile";
	}
	
	@ResponseBody
	@PostMapping(value = "/check-archive-name-existence")
	public GeekilyMap checkArchiveNameExistence(@RequestBody GeekilyMap gMap) throws Exception{
		try {
			if(userService.checkArchiveNameExistence(gMap)) {
				throw new Exception("The archive name has already been signed up. Please try another archive name.");
			}
			if(Arrays.asList(Constants.BANNED_ARCHIVE_NAME).contains(gMap.getString("archiveName"))) {
				throw new Exception("The archive name has already been signed up. Please try another archive name.");
			}
		} catch (Exception e) {
			gMap.setResultCode(0);
			gMap.setResultMessage(e.getMessage());
		}
		return gMap;
	}
	
	@ResponseBody
	@PostMapping(value = "/check-archive-name-update-available")
	public GeekilyMap checkArchiveNameUpdateAvailable(@RequestBody GeekilyMap gMap) throws Exception{
		try {
			if(!userService.checkArchiveNameUpaateAvailable(gMap)) {
				throw new Exception("Archive name cannot be updated for 1 week since the last update.");
			}
		} catch (Exception e) {
			gMap.setResultCode(0);
			gMap.setResultMessage(e.getMessage());
		}
		return gMap;
	}
	
	@ResponseBody
	@PostMapping(value = "/save-profile", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public GeekilyMap saveProfile(@RequestPart(value = "profileImage", required = false) MultipartFile thumbnail, @RequestPart("data") GeekilyMap gMap) throws Exception{
		
		String url = Util.getFileServerUploadUrl() + "/file";
		
		try {
			if(thumbnail != null) {
				GeekilyConnector gConntector = new GeekilyConnector.Builder(url).data(thumbnail).build();
				GeekilyMap responseMap = gConntector.sendMultiPartFile("file");
				gMap.put("profileImageUid", responseMap.getString("fileUid"));
			}
			
			userService.updateUser(gMap);
			gMap.setResultMessage("Your request is complete.");
		} catch (Exception e) {
			gMap.setResultCode(0);
			gMap.setResultMessage(e.getMessage());
		}
		return gMap;
	}
	
	@ResponseBody
	@PostMapping(value = "/delete-user")
	public GeekilyMap deleteUser(HttpSession session) throws Exception{
		GeekilyMap gMap = new GeekilyMap();
		try {
			userService.deleteUser(gMap);
			gMap.setResultMessage("Thank you for using Geekily Archive.");
			session.invalidate();
		} catch (Exception e) {
			gMap.setResultCode(0);
			gMap.setResultMessage(e.getMessage());
		}
		return gMap;
	}
	
	@GetMapping(value = "/socialmedia")
	public String socialmedia(HttpServletRequest request, ModelMap modelMap) throws Exception{
		List<GeekilyMap> socialMediaList = userService.selectSocialMedia(new GeekilyMap());
		modelMap.put("socialMediaList", socialMediaList);
		return "user/socialmedia/socialmedia";
	}
	
	@ResponseBody
	@PostMapping(value = "/save-socialmedia", consumes = MediaType.APPLICATION_JSON_VALUE)
	public GeekilyMap socialmediaSave(@RequestBody List<GeekilyMap> socialMediaList) throws Exception{
		GeekilyMap gMap = new GeekilyMap();
		try {
			userService.deleteSocialMeida(gMap);
			for(GeekilyMap socialMedia : socialMediaList) {
				socialMedia.put("socialMediaUid", Util.generateUID("SMD"));
	            userService.insertSocialMedia(socialMedia);
	        }
		} catch (Exception e) {
			gMap.put("resultCode"	, 0);
			gMap.put("resultMessage", e.getMessage());
		}
		return gMap;
	}
}
