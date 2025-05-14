package com.geekily.geekilyArchiveAdmin.mapper.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekily.geekilyArchiveAdmin.common.Util;
import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveAdmin.mapper.CommonMapper;
import com.geekily.geekilyArchiveAdmin.mapper.LoginMapper;
import com.geekily.geekilyArchiveAdmin.vo.CommonVO;

@Service
public class CommonService implements CommonMapper{
	
	@Autowired
	CommonMapper commonMapper;

	@Override
	public List<Map<String, Object>> selectAllMenu(Object object) {
		return commonMapper.selectAllMenu(object);
	}

	@Override
	public String selectSelectedMenuTitle(String path) {
		return commonMapper.selectSelectedMenuTitle(path);
	}
	
	
}
