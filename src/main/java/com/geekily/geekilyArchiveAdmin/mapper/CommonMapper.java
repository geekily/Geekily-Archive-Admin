package com.geekily.geekilyArchiveAdmin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;

@Mapper
public interface CommonMapper {
	public List<Map<String, Object>> selectAllMenu(Object object);
	public String selectSelectedMenuTitle(String path);
	public String selectProfileImageUrlPath(GeekilyMap gMap);
}
