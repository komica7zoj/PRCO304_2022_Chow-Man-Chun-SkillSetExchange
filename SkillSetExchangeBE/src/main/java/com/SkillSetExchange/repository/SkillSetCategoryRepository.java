package com.SkillSetExchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SkillSetExchange.models.SkillSetCategory;
import com.SkillSetExchange.models.SkillSetType;

@Repository
public interface SkillSetCategoryRepository extends JpaRepository<SkillSetCategory, Long>{
    @Transactional
    @Query(value ="select * from skill_set_category where skill_set_type_id in ( :id )", 
    		  nativeQuery = true)
    List<SkillSetCategory> getSkillSetCategory(@Param("id") List<Long> id);  
    
}
