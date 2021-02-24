package SkillSetExchange.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


import SkillSetExchange.model.UserInfoDAO;
import SkillSetExchange.service.LoginVaildService;



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
