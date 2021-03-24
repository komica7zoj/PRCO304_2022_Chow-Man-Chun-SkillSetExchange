package com.SkillSetExchange.models.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.SkillSetExchange.models.SkillSetCategory;
import com.SkillSetExchange.models.SkillSetInfo;
import com.SkillSetExchange.models.SkillSetType;
import com.SkillSetExchange.models.TackleInfo;
import com.SkillSetExchange.repository.dao.SkillSetSearchResultDAO;

import lombok.Data;

@Data
@Component
public class SearchConditionDAO {
	public String name;
	public List<SkillSetType> skillSetType;
	public List<SkillSetCategory> skillSetCategory;
	public List<TackleInfo> tackleInfo;
	public List<SkillSetInfo> skillSetInfo;
public List<SkillSetSearchResultDAO> skillSetSearchResultDAO;
}
