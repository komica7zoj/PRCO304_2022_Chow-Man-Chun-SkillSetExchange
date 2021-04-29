package com.SkillSetExchange.services;

import java.util.List;

import com.SkillSetExchange.models.TackleInfo;
import com.SkillSetExchange.repository.dao.TackleViewDAO;

public interface TackleService {
	List<TackleViewDAO> getTackleInfoByUsername(String username);
	int newTackleIssue(TackleInfo tackleInfo);
	List<TackleViewDAO> getTackleInfoByTackleName(String tackleName);
}
