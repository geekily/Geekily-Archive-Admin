package com.geekive.geekiveArchiveAdmin.mapper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekive.geekiveArchiveAdmin.common.CryptoUtil;
import com.geekive.geekiveArchiveAdmin.common.Util;
import com.geekive.geekiveArchiveAdmin.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveAdmin.mapper.UserMapper;

@Service
public class UserService implements UserMapper{
	
	@Autowired
    private CryptoUtil cryptoUtil;
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public GeekiveMap selectUser(GeekiveMap gMap) {
		GeekiveMap userMap = userMapper.selectUser(gMap);
		try {
			userMap.put("email"		, cryptoUtil.decryptData(userMap.getString("email")));
			userMap.put("userName"	, cryptoUtil.decryptData(userMap.getString("userName")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userMap;
	}

	@Override
	public Boolean checkArchiveNameExistence(GeekiveMap gMap) {
		return userMapper.checkArchiveNameExistence(gMap);
	}

	@Override
	public Boolean checkArchiveNameUpaateAvailable(GeekiveMap gMap) {
		return userMapper.checkArchiveNameUpaateAvailable(gMap);
	}

	@Override
	public int updateUser(GeekiveMap gMap) {
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
	public int deleteUser(GeekiveMap gMap) {
		return userMapper.deleteUser(gMap);
	}

	@Override
	public List<GeekiveMap> selectSocialMedia(GeekiveMap gMap) {
		return userMapper.selectSocialMedia(gMap);
	}

	@Override
	public int insertSocialMedia(GeekiveMap gMap) {
		return userMapper.insertSocialMedia(gMap);
	}
	
	@Override
	public int deleteSocialMeida(GeekiveMap gMap) {
		return userMapper.deleteSocialMeida(gMap);
	}
}
