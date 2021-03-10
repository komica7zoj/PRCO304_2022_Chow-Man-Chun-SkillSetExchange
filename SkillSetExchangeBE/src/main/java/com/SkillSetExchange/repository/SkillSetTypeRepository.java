package com.SkillSetExchange.repository;



import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SkillSetExchange.models.Role;
import com.SkillSetExchange.models.SkillSetCategory;
import com.SkillSetExchange.models.SkillSetType;
import com.SkillSetExchange.models.DAO.SearchConditionDAO;


@Repository
public interface SkillSetTypeRepository  extends JpaRepository<SkillSetType, Long>{
	
	
    @Transactional
    @Query(value ="select * from skill_set_type", 
    		  nativeQuery = true)
    List<SkillSetType> getAllSkillSetType();
    

    
}
