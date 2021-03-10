package com.SkillSetExchange.services;

import com.SkillSetExchange.models.DAO.SearchConditionDAO;

public interface SearchSkillSetService {

	SearchConditionDAO getAllSkillSetTpe();
	SearchConditionDAO getSkillSetCategory(SearchConditionDAO searchConditionDAO );
	SearchConditionDAO findAllMatchTackle(SearchConditionDAO searchConditionDAO );
}
