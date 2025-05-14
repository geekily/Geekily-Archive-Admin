package com.geekily.geekilyArchiveAdmin.mapper.service;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveAdmin.mapper.LoginMapper;
import com.geekily.geekilyArchiveAdmin.mapper.SystemMapper;

@Service
public class SystemService implements SystemMapper{
	
	@Autowired
	SystemMapper systemMapper;

	@Override
	public List<Map<String, Object>> selectAllMenu() {
		return systemMapper.selectAllMenu();
	}

	@Override
	public int insertMenu(GeekilyMap gMap) {
		return systemMapper.insertMenu(gMap);
	}

	@Override
	public int updateMenu(GeekilyMap gMap) {
		return systemMapper.updateMenu(gMap);
	}

	@Override
	public int deleteMenu(GeekilyMap gMap) {
		return systemMapper.deleteMenu(gMap);
	}
}
