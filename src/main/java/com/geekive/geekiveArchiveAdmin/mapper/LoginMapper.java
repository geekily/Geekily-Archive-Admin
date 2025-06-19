package com.geekive.geekiveArchiveAdmin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.geekive.geekiveArchiveAdmin.geekiveCustom.GeekiveMap;

@Mapper
public interface LoginMapper {
	public int checkEmail(GeekiveMap gMap);
	public GeekiveMap selectUser(GeekiveMap gMap);
}
