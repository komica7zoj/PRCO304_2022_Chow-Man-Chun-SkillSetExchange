package com.SkillSetExchange.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SkillSetExchange.models.TackleInfo;
import com.SkillSetExchange.models.DAO.SearchConditionDAO;
import com.SkillSetExchange.models.DAO.TackleInfoDAO;
import com.SkillSetExchange.repository.TackleRepository;
import com.SkillSetExchange.services.TackleService;

@Service
public class TackleServiceImpl implements TackleService {
	@Autowired
	TackleRepository tackleRepository;
	

	
	@Override
	public  int newTackleIssue(TackleInfo tackleInfo) {
		// TODO Auto-generated method stub
		
			return tackleRepository.newTackle(tackleInfo);
	}


	@Override
	public TackleInfoDAO getTackleInfoByUsername(String username) {
		// TODO Auto-generated method stub
		return new TackleInfoDAO() {{tackleRepository.getTackleByUsername(username);}};
	}


	@Override
	public TackleInfoDAO getTackleInfoByTackleName(String tackleName) {
		// TODO Auto-generated method stub
		return new TackleInfoDAO() {{tackleRepository.getTackleByTacklename(tackleName);}};

	}
}
