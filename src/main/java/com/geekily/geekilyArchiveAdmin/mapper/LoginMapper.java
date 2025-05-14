package com.geekily.geekilyArchiveAdmin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;

@Mapper
public interface LoginMapper {
	public int checkEmail(GeekilyMap gMap);
	public GeekilyMap selectUser(GeekilyMap gMap);
}
