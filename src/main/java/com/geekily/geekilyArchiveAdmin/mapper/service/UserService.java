package com.geekily.geekilyArchiveAdmin.mapper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekily.geekilyArchiveAdmin.common.CryptoUtil;
import com.geekily.geekilyArchiveAdmin.common.Util;
import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveAdmin.mapper.UserMapper;

@Service
public class UserService implements UserMapper{
	
	@Autowired
    private CryptoUtil cryptoUtil;
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public GeekilyMap selectUser(GeekilyMap gMap) {
		GeekilyMap userMap = userMapper.selectUser(gMap);
		try {
			userMap.put("email"		, cryptoUtil.decryptData(userMap.getString("email")));
			userMap.put("userName"	, cryptoUtil.decryptData(userMap.getString("userName")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userMap;
	}

	@Override
	public Boolean checkArchiveNameExistence(GeekilyMap gMap) {
		return userMapper.checkArchiveNameExistence(gMap);
	}

	@Override
	public Boolean checkArchiveNameUpaateAvailable(GeekilyMap gMap) {
		return userMapper.checkArchiveNameUpaateAvailable(gMap);
	}

	@Override
	public int updateUser(GeekilyMap gMap) {
		try {
			if(Util.isNotEmpty(gMap.getString("password"))) {
				gMap.put("password", cryptoUtil.encryptPassword(gMap.getString("password")));	
			}
			gMap.put("userName", cryptoUtil.encryptData(gMap.getString("userName")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userMapper.updateUser(gMap);
	}
	
	@Override
	public int deleteUser(GeekilyMap gMap) {
		return userMapper.deleteUser(gMap);
	}

	@Override
	public List<GeekilyMap> selectSocialMedia(GeekilyMap gMap) {
		return userMapper.selectSocialMedia(gMap);
	}

	@Override
	public int insertSocialMedia(GeekilyMap gMap) {
		return userMapper.insertSocialMedia(gMap);
	}
	
	@Override
	public int deleteSocialMeida(GeekilyMap gMap) {
		return userMapper.deleteSocialMeida(gMap);
	}
}
