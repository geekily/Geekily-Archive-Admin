package com.geekive.geekiveArchiveAdmin.mapper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekive.geekiveArchiveAdmin.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveAdmin.mapper.HomeMapper;

@Service
public class HomeService implements HomeMapper{
	
	@Autowired
	HomeMapper homeMapper;

	@Override
	public List<GeekiveMap> selectTrafficTrendForMyArchive(GeekiveMap gMap) {
		return homeMapper.selectTrafficTrendForMyArchive(gMap);
	}

	@Override
	public List<GeekiveMap> selectTop5MostViewedArticlesInTheLast30Days(GeekiveMap gMap) {
		return homeMapper.selectTop5MostViewedArticlesInTheLast30Days(gMap);
	}
}
