package com.geekive.geekiveArchiveAdmin.mapper.service;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekive.geekiveArchiveAdmin.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveAdmin.mapper.LoginMapper;
import com.geekive.geekiveArchiveAdmin.mapper.SystemMapper;

@Service
public class SystemService implements SystemMapper{
	
	@Autowired
	SystemMapper systemMapper;

	@Override
	public List<Map<String, Object>> selectAllMenu() {
		return systemMapper.selectAllMenu();
	}

	@Override
	public int insertMenu(GeekiveMap gMap) {
		return systemMapper.insertMenu(gMap);
	}

	@Override
	public int updateMenu(GeekiveMap gMap) {
		return systemMapper.updateMenu(gMap);
	}

	@Override
	public int deleteMenu(GeekiveMap gMap) {
		return systemMapper.deleteMenu(gMap);
	}
}
