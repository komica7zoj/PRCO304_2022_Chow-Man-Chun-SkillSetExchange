package com.SkillSetExchange.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SkillSetExchange.models.DAO.SearchConditionDAO;
import com.SkillSetExchange.repository.SkillSetCategoryRepository;
import com.SkillSetExchange.repository.SkillSetInfoRepository;
import com.SkillSetExchange.repository.SkillSetTypeRepository;
import com.SkillSetExchange.services.SearchSkillSetService;



@Service("searchSkillSetService")
public class SearchSkillSetServiceImpl implements SearchSkillSetService {

	@Autowired
	SkillSetTypeRepository skillSetTypeRepository;
	
	@Autowired
	SkillSetCategoryRepository skillSetCategoryRepository;
	@Autowired
	SkillSetInfoRepository skillSetInfoRepository;
	
	@Override
	public SearchConditionDAO getAllSkillSetType() {
		// TODO Auto-generated method stub
		
		return new SearchConditionDAO(){{skillSetType = skillSetTypeRepository.getAllSkillSetType();}};
	}

	@Override
	public SearchConditionDAO getSkillSetCategory(SearchConditionDAO searchConditionDAO) {
		// TODO Auto-generated method stub
		List<Long> idList = new ArrayList();
		searchConditionDAO.skillSetType.forEach(t->{
			idList.add(t.id);
			
		});
		return new SearchConditionDAO(){{skillSetCategory = skillSetCategoryRepository.getSkillSetCategory( idList);}};
	}

	@Override
	public SearchConditionDAO findAllMatchTackle(SearchConditionDAO searchConditionDAO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchConditionDAO findSkillSet(SearchConditionDAO searchConditionDAO) {
		// TODO Auto-generated method stub
		List<Long> idList = new ArrayList();
		searchConditionDAO.skillSetCategory.forEach(t->{
			idList.add(t.id);
			
		});
		return new SearchConditionDAO(){{
			skillSetInfo = skillSetInfoRepository.getSkillSetInfo( idList);
		    skillSetCategory=searchConditionDAO.skillSetCategory;
		    skillSetSearchResultDAO = skillSetInfoRepository.getSkillSetInfoDAO( idList);
		}};
	}

}
