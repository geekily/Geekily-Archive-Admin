package com.geekive.geekiveArchiveAdmin.mapper;

import java.util.List;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.geekive.geekiveArchiveAdmin.vo.CommonVO;
import com.geekive.geekiveArchiveAdmin.vo.OptionVO;

@Mapper
public interface OptionMapper {
	
	/* checkbox, radio */
	public int checkCommonCode(OptionVO optionVO);
	public List<OptionVO> selectCode(OptionVO optionVO);
	public List<OptionVO> selectCategory(OptionVO optionVO);
	public OptionVO selectFile(OptionVO optionVO);
}
