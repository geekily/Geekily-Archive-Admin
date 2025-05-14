package com.geekily.geekilyArchiveAdmin.mapper;

import java.util.List;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;

@Mapper
public interface SystemMapper {
	public List<Map<String, Object>> selectAllMenu();
	public int insertMenu(GeekilyMap gMap);
	public int updateMenu(GeekilyMap gMap);
	public int deleteMenu(GeekilyMap gMap);
}
