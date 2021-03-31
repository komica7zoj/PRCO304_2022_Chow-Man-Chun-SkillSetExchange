package com.SkillSetExchange.services;

import com.SkillSetExchange.models.DAO.SearchConditionDAO;

public interface SearchSkillSetService {
	SearchConditionDAO findSkillSet(SearchConditionDAO searchConditionDAO);
	SearchConditionDAO getAllSkillSetType();
	SearchConditionDAO getSkillSetCategory(SearchConditionDAO searchConditionDAO );
	SearchConditionDAO findAllMatchTackle(SearchConditionDAO searchConditionDAO );
	SearchConditionDAO getSkillSetByid(Long id);
	SearchConditionDAO getSkillSetByName(String username);
}
