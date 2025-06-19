package com.geekive.geekiveArchiveAdmin.mapper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekive.geekiveArchiveAdmin.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveAdmin.mapper.OptionMapper;
import com.geekive.geekiveArchiveAdmin.vo.OptionVO;

@Service
public class OptionService implements OptionMapper{
	
	@Autowired
	OptionMapper optionMapper;

	public Boolean isCommonCode(OptionVO optionVO) {
		return checkCommonCode(optionVO) == 1 ? true : false;
	}
	
	@Override
	public int checkCommonCode(OptionVO optionVO) {
		return optionMapper.checkCommonCode(optionVO);
	}
	
	@Override
	public List<OptionVO> selectCode(OptionVO optionVO) {
		return optionMapper.selectCode(optionVO);
	}

	@Override
	public List<OptionVO> selectCategory(OptionVO optionVO) {
		optionVO.setUserUid(new GeekiveMap().getString("userUid"));
		return optionMapper.selectCategory(optionVO);
	}

	@Override
	public OptionVO selectFile(OptionVO optionVO) {
		return optionMapper.selectFile(optionVO);
	}
	
	
}
