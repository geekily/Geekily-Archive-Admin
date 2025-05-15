package com.geekily.geekilyArchiveAdmin.mapper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekily.geekilyArchiveAdmin.common.CryptoUtil;
import com.geekily.geekilyArchiveAdmin.common.Util;
import com.geekily.geekilyArchiveAdmin.geekilyCustom.GeekilyMap;
import com.geekily.geekilyArchiveAdmin.mapper.LoginMapper;

@Service
public class LoginService implements LoginMapper{
	
	@Autowired
    private CryptoUtil cryptoUtil;
	
	@Autowired
	LoginMapper loginMapper;

	@Override
	public int checkEmail(GeekilyMap gMap) {
		try {
			gMap.put("email", cryptoUtil.encryptData(gMap.getString("email")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginMapper.checkEmail(gMap);
	}

	@Override
	public GeekilyMap selectUser(GeekilyMap gMap) {
		GeekilyMap userMap = null;
		try {
			// email login
			if(Util.isNotEmpty(gMap.getString("email"))) {
				gMap.put("email", cryptoUtil.encryptData(gMap.getString("email")));
				userMap = loginMapper.selectUser(gMap);
			    if(Util.isEmpty(userMap) || !cryptoUtil.checkPassword(gMap.getString("password"), userMap.getString("password"))) {
			        return null;
			    }else {
			    	userMap.remove("password");
			    	userMap.put("userName", cryptoUtil.decryptData(userMap.getString("userName")));
			    }
			}
			
			// jwt login
			if(Util.isNotEmpty(gMap.getString("userUid"))) {
				userMap = loginMapper.selectUser(gMap);
				if(Util.isEmpty(userMap)) {
					return null;
				}else {
			    	userMap.remove("password");
			    	userMap.put("userName", cryptoUtil.decryptData(userMap.getString("userName")));
				}
			}
			
			if(Util.isNotEmpty(userMap)) {
				userMap.put("myArchiveFullUrlPath", Util.getUserServerUrl() + userMap.getString("myArchiveUrlPath"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userMap;
	}
}
