package com.geekive.geekiveArchiveAdmin.mapper;

import java.util.List;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.geekive.geekiveArchiveAdmin.geekiveCustom.GeekiveMap;

@Mapper
public interface SystemMapper {
	public List<Map<String, Object>> selectAllMenu();
	public int insertMenu(GeekiveMap gMap);
	public int updateMenu(GeekiveMap gMap);
	public int deleteMenu(GeekiveMap gMap);
}
