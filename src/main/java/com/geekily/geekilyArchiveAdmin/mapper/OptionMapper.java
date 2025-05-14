package com.geekily.geekilyArchiveAdmin.mapper;

import java.util.List;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.geekily.geekilyArchiveAdmin.vo.CommonVO;
import com.geekily.geekilyArchiveAdmin.vo.OptionVO;

@Mapper
public interface OptionMapper {
	
	/* checkbox, radio */
	public int checkCommonCode(OptionVO optionVO);
	public List<OptionVO> selectCode(OptionVO optionVO);
	public List<OptionVO> selectCategory(OptionVO optionVO);
	public OptionVO selectFile(OptionVO optionVO);
}
