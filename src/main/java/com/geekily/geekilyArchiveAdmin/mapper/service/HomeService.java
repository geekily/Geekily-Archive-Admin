package com.geekily.geekilyArchiveAdmin.mapper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveAdmin.mapper.HomeMapper;

@Service
public class HomeService implements HomeMapper{
	
	@Autowired
	HomeMapper homeMapper;

	@Override
	public List<GeekilyMap> selectTrafficTrendForMyArchive(GeekilyMap gMap) {
		return homeMapper.selectTrafficTrendForMyArchive(gMap);
	}

	@Override
	public List<GeekilyMap> selectTop5MostViewedArticlesInTheLast30Days(GeekilyMap gMap) {
		return homeMapper.selectTop5MostViewedArticlesInTheLast30Days(gMap);
	}
}
