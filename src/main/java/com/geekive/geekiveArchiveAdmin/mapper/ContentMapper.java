package com.geekive.geekiveArchiveAdmin.mapper;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.geekive.geekiveArchiveAdmin.geekiveCustom.GeekiveMap;

@Mapper
public interface ContentMapper {
	public int selectArticleListCount(GeekiveMap gMap);
	public List<GeekiveMap> selectArticleList(GeekiveMap gMap);
	public GeekiveMap selectArticleForView(GeekiveMap gMap);
	public GeekiveMap selectArticleForEdit(GeekiveMap gMap);
	public int upsertArticle(GeekiveMap gMap);
	public int deleteArticle(GeekiveMap gMap);
	public List<Map<String, Object>> selectCategory(GeekiveMap gMap);
	public int updateCategoryOrder(GeekiveMap gMap);
	public int insertCategory(GeekiveMap gMap);
	public int updateCategory(GeekiveMap gMap);
	public int deleteCategory(GeekiveMap gMap);
}
