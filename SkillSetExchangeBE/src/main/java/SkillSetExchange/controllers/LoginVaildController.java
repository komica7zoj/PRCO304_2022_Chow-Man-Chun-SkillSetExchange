package SkillSetExchange.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import SkillSetExchange.model.UserInfoDAO;
import SkillSetExchange.services.LoginVaildService;

@RestController
@RequestMapping(path = "/skillsetexchange")
public class LoginVaildController {
	
	@Autowired
	LoginVaildService loginVaildService;
	
	static Logger log = Logger.getLogger(LoginVaildController.class.getName());
	@ResponseBody
	@RequestMapping(path="/vaild",method=RequestMethod.POST)
    public String greetingText(@RequestBody UserInfoDAO userInfoDAO) {
		log.info("userInfoDAO: "+userInfoDAO);
		if (loginVaildService.userVaild(userInfoDAO))
		{
			 return "Hello " + userInfoDAO.username + "!";
		}
        return "Error " + userInfoDAO.username + "!";
    }
}
