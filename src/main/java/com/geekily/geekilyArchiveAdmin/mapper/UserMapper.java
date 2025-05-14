package com.geekily.geekilyArchiveAdmin.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;

@Mapper
public interface UserMapper {
	public GeekilyMap selectUser(GeekilyMap gMap);
	public Boolean checkArchiveNameExistence(GeekilyMap gMap);
	public Boolean checkArchiveNameUpaateAvailable(GeekilyMap gMap);
	public int updateUser(GeekilyMap gMap);
	public int deleteUser(GeekilyMap gMap);
	public List<GeekilyMap> selectSocialMedia(GeekilyMap gMap);
	public int insertSocialMedia(GeekilyMap gMap);
	public int deleteSocialMeida(GeekilyMap gMap);
}
