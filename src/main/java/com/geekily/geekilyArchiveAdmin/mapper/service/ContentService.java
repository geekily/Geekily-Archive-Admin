package com.geekily.geekilyArchiveAdmin.mapper.service;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekily.geekilyArchiveAdmin.common.CryptoUtil;
import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveAdmin.mapper.ContentMapper;
import com.geekily.geekilyArchiveAdmin.mapper.LoginMapper;
import com.geekily.geekilyArchiveAdmin.mapper.SystemMapper;

@Service
public class ContentService implements ContentMapper{
	
	@Autowired
    private CryptoUtil cryptoUtil;
	
	@Autowired
	ContentMapper contentMapper;

	@Override
	public int selectArticleListCount(GeekilyMap gMap) {
		return contentMapper.selectArticleListCount(gMap);
	}

	@Override
	public List<GeekilyMap> selectArticleList(GeekilyMap gMap) {
		List<GeekilyMap> articleList = contentMapper.selectArticleList(gMap);
		try {
			for(GeekilyMap article : articleList) {
				article.put("registrationUser", cryptoUtil.decryptData(article.getString("registrationUser")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return articleList;
	}
	
	@Override
	public GeekilyMap selectArticleForView(GeekilyMap gMap) {
		return contentMapper.selectArticleForView(gMap);
	}
	
	@Override
	public GeekilyMap selectArticleForEdit(GeekilyMap gMap) {
		return contentMapper.selectArticleForEdit(gMap);
	}
	
	@Override
	public int upsertArticle(GeekilyMap gMap) {
		return contentMapper.upsertArticle(gMap);
	}

	@Override
	public int deleteArticle(GeekilyMap gMap) {
		return contentMapper.deleteArticle(gMap);
	}

	@Override
	public List<Map<String, Object>> selectCategory(GeekilyMap gMap) {
		return contentMapper.selectCategory(gMap);
	}

	@Override
	public int updateCategoryOrder(GeekilyMap gMap) {
		return contentMapper.updateCategoryOrder(gMap);
	}

	@Override
	public int insertCategory(GeekilyMap gMap) {
		return contentMapper.insertCategory(gMap);
	}

	@Override
	public int updateCategory(GeekilyMap gMap) {
		return contentMapper.updateCategory(gMap);
	}

	@Override
	public int deleteCategory(GeekilyMap gMap) {
		return contentMapper.deleteCategory(gMap);
	}
}
