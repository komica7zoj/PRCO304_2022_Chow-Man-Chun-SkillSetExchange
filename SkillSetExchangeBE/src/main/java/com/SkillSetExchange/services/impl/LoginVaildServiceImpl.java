package com.SkillSetExchange.services.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.SkillSetExchange.models.UserInfoDAO;
import com.SkillSetExchange.services.LoginVaildService;



@Service("loginVaildService")
public class LoginVaildServiceImpl implements LoginVaildService {
	static Logger log = Logger.getLogger(LoginVaildServiceImpl.class.getName());
	@Override
	public boolean userVaild(UserInfoDAO userInfoDAO) {
		// TODO Auto-generated method stub
		log.info("to verify: "+userInfoDAO);
		
		return true;
	}

}
