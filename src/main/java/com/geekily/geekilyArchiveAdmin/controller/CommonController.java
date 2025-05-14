package com.geekily.geekilyArchiveAdmin.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.geekily.geekilyArchiveAdmin.common.Util;
import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveAdmin.mapper.service.CommonService;

@Controller
public class CommonController {

	@Resource
	CommonService commonService;

	@GetMapping(value = "/menu")
	public String menu(HttpServletRequest request, ModelMap modelMap) throws Exception{

		List<Map<String, Object>> menuList = null;
		
		try {
			String subPath = request.getAttribute("subPath").toString();
			GeekilyMap gMap = new GeekilyMap();
			gMap.put("subPathArray", Util.splitSubPath(subPath));
			
			menuList = commonService.selectAllMenu(gMap);
			menuList = Util.hierarchicalSortList("menuUid", "parentMenuUid", "depth", "order", menuList);
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		modelMap.addAttribute("menuList", menuList);
		return "common/menu";
	}
}
