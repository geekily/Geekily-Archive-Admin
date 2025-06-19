package com.geekive.geekiveArchiveAdmin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.geekive.geekiveArchiveAdmin.geekiveCustom.GeekiveMap;

@Mapper
public interface CommonMapper {
	public List<Map<String, Object>> selectAllMenu(Object object);
	public String selectSelectedMenuTitle(String path);
	public String selectProfileImageUrlPath(GeekiveMap gMap);
}
