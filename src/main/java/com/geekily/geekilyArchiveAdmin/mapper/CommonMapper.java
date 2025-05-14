package com.geekily.geekilyArchiveAdmin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {
	public List<Map<String, Object>> selectAllMenu(Object object);
	public String selectSelectedMenuTitle(String path);
}
