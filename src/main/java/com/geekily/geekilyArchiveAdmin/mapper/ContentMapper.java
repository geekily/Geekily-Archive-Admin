package com.geekily.geekilyArchiveAdmin.mapper;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;

@Mapper
public interface ContentMapper {
	public int selectArticleListCount(GeekilyMap gMap);
	public List<GeekilyMap> selectArticleList(GeekilyMap gMap);
	public GeekilyMap selectArticleForView(GeekilyMap gMap);
	public GeekilyMap selectArticleForEdit(GeekilyMap gMap);
	public int upsertArticle(GeekilyMap gMap);
	public int deleteArticle(GeekilyMap gMap);
	public List<Map<String, Object>> selectCategory(GeekilyMap gMap);
	public int updateCategoryOrder(GeekilyMap gMap);
	public int insertCategory(GeekilyMap gMap);
	public int updateCategory(GeekilyMap gMap);
	public int deleteCategory(GeekilyMap gMap);
}
