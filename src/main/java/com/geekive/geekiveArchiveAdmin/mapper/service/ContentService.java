package com.geekive.geekiveArchiveAdmin.mapper.service;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekive.geekiveArchiveAdmin.common.CryptoUtil;
import com.geekive.geekiveArchiveAdmin.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveAdmin.mapper.ContentMapper;
import com.geekive.geekiveArchiveAdmin.mapper.LoginMapper;
import com.geekive.geekiveArchiveAdmin.mapper.SystemMapper;

@Service
public class ContentService implements ContentMapper{
	
	@Autowired
    private CryptoUtil cryptoUtil;
	
	@Autowired
	ContentMapper contentMapper;

	@Override
	public int selectArticleListCount(GeekiveMap gMap) {
		return contentMapper.selectArticleListCount(gMap);
	}

	@Override
	public List<GeekiveMap> selectArticleList(GeekiveMap gMap) {
		List<GeekiveMap> articleList = contentMapper.selectArticleList(gMap);
		try {
			for(GeekiveMap article : articleList) {
				article.put("registrationUser", cryptoUtil.decryptData(article.getString("registrationUser")));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return articleList;
	}
	
	@Override
	public GeekiveMap selectArticleForView(GeekiveMap gMap) {
		return contentMapper.selectArticleForView(gMap);
	}
	
	@Override
	public GeekiveMap selectArticleForEdit(GeekiveMap gMap) {
		return contentMapper.selectArticleForEdit(gMap);
	}
	
	@Override
	public int upsertArticle(GeekiveMap gMap) {
		return contentMapper.upsertArticle(gMap);
	}

	@Override
	public int deleteArticle(GeekiveMap gMap) {
		return contentMapper.deleteArticle(gMap);
	}

	@Override
	public List<Map<String, Object>> selectCategory(GeekiveMap gMap) {
		return contentMapper.selectCategory(gMap);
	}

	@Override
	public int updateCategoryOrder(GeekiveMap gMap) {
		return contentMapper.updateCategoryOrder(gMap);
	}

	@Override
	public int insertCategory(GeekiveMap gMap) {
		return contentMapper.insertCategory(gMap);
	}

	@Override
	public int updateCategory(GeekiveMap gMap) {
		return contentMapper.updateCategory(gMap);
	}

	@Override
	public int deleteCategory(GeekiveMap gMap) {
		return contentMapper.deleteCategory(gMap);
	}
}
