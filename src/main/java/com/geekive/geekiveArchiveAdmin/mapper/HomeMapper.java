package com.geekive.geekiveArchiveAdmin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.geekive.geekiveArchiveAdmin.geekiveCustom.GeekiveMap;

@Mapper
public interface HomeMapper {
	public List<GeekiveMap> selectTrafficTrendForMyArchive(GeekiveMap gMap);
	public List<GeekiveMap> selectTop5MostViewedArticlesInTheLast30Days(GeekiveMap gMap);
}
