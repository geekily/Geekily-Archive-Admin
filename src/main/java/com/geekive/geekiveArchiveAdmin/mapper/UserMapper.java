package com.geekive.geekiveArchiveAdmin.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.geekive.geekiveArchiveAdmin.geekiveCustom.GeekiveMap;

@Mapper
public interface UserMapper {
	public GeekiveMap selectUser(GeekiveMap gMap);
	public Boolean checkArchiveNameExistence(GeekiveMap gMap);
	public Boolean checkArchiveNameUpaateAvailable(GeekiveMap gMap);
	public int updateUser(GeekiveMap gMap);
	public int deleteUser(GeekiveMap gMap);
	public List<GeekiveMap> selectSocialMedia(GeekiveMap gMap);
	public int insertSocialMedia(GeekiveMap gMap);
	public int deleteSocialMeida(GeekiveMap gMap);
}
