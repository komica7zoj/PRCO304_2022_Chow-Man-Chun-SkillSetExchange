package com.SkillSetExchange.services;

import com.SkillSetExchange.models.TackleInfo;
import com.SkillSetExchange.models.DAO.SearchConditionDAO;
import com.SkillSetExchange.models.DAO.TackleInfoDAO;

public interface TackleService {
	TackleInfoDAO getTackleInfo(SearchConditionDAO searchConditionDAO);
	int newTackleIssue(TackleInfo tackleInfo);
}
