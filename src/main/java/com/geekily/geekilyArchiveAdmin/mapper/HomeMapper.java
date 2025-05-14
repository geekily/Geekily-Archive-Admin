package com.geekily.geekilyArchiveAdmin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;

@Mapper
public interface HomeMapper {
	public List<GeekilyMap> selectTrafficTrendForMyArchive(GeekilyMap gMap);
	public List<GeekilyMap> selectTop5MostViewedArticlesInTheLast30Days(GeekilyMap gMap);
}
