package com.geekily.geekilyArchiveAdmin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geekily.geekilyArchiveAdmin.common.Util;
import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveAdmin.mapper.service.HomeService;


@Controller
public class HomeController {
	
	@Resource
	HomeService homeService;

	@GetMapping(value = {"", "/index", "/home"})
	public String index(ModelMap modelMap) throws Exception{
		List<GeekilyMap> trafficTrendList = homeService.selectTrafficTrendForMyArchive(new GeekilyMap());
		modelMap.put("trafficTrendList", trafficTrendList);
		
		List<GeekilyMap> top5ArticleList = homeService.selectTop5MostViewedArticlesInTheLast30Days(new GeekilyMap());
		modelMap.put("top5ArticleList", top5ArticleList);
		
		return "home/home";
	}
	
	@ResponseBody
	@PostMapping(value = "/errorImage")
	public String errorImage(@RequestBody GeekilyMap gMap) throws Exception{
		String storageServerUrl = Util.getStorageUrl();
		switch (gMap.getString("type")) {
		case "profile":
			storageServerUrl += "/image/profile/basic.jpg";
			break;
		case "thumbnail":
			storageServerUrl += "/image/article/no_thumbnail.png";
			break;
		default:
			break;
		}
		return storageServerUrl;
	}
}
