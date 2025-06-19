package com.geekive.geekiveArchiveAdmin.mapper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekive.geekiveArchiveAdmin.common.CryptoUtil;
import com.geekive.geekiveArchiveAdmin.common.Util;
import com.geekive.geekiveArchiveAdmin.geekiveCustom.GeekiveMap;
import com.geekive.geekiveArchiveAdmin.mapper.LoginMapper;

@Service
public class LoginService implements LoginMapper{
	
	@Autowired
    private CryptoUtil cryptoUtil;
	
	@Autowired
	LoginMapper loginMapper;

	@Override
	public int checkEmail(GeekiveMap gMap) {
		try {
			gMap.put("email", cryptoUtil.encryptData(gMap.getString("email")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginMapper.checkEmail(gMap);
	}

	@Override
	public GeekiveMap selectUser(GeekiveMap gMap) {
		GeekiveMap userMap = null;
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
