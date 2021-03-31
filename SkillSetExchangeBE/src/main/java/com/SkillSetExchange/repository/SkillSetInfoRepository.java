package com.SkillSetExchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SkillSetExchange.models.SkillSetCategory;
import com.SkillSetExchange.models.SkillSetInfo;
import com.SkillSetExchange.repository.dao.SkillSetSearchResultDAO;


@Repository
public interface SkillSetInfoRepository extends CrudRepository<SkillSetInfo,Long> {

	   @Transactional
	    @Query(value ="select * from skill_set_info where skill_set_category_id in ( :id )", 
	    		  nativeQuery = true)
	    List<SkillSetInfo> getSkillSetInfo(@Param("id") List<Long> id);  
	   
	   @Transactional
	    @Query(value ="select * from skill_set_info where id in ( :id )", 
	    		  nativeQuery = true)
	    List<SkillSetInfo> getSkillSetInfoById(@Param("id") Long id); 
	   
	   @Transactional
	    @Query(value ="select s.id as id, s.skill_set_name as skillSetName,s.skill_set_descript as  skillSetDescript,s.username as username ,s.academic_level as academicLevel,s.tag as tag,c.category_name as skillCategoryName "
	    		+ "from skill_set_info s "
	    		+ ", skill_set_category c "
	    		+ "where s.skill_set_category_id in ( :id ) and c.id = s.skill_set_category_id ", 
	    		  nativeQuery = true)
	    List<SkillSetSearchResultDAO> getSkillSetInfoDAO(@Param("id") List<Long> id); 
	   
	   @Transactional
	    @Query(value ="select s.id as id, s.skill_set_name as skillSetName,s.skill_set_descript as  skillSetDescript,s.username as username ,s.academic_level as academicLevel,s.tag as tag,c.category_name as skillCategoryName "
	    		+ "from skill_set_info s "
	    		+ ", skill_set_category c "
	    		+ "where s.id in ( :id ) and c.id = s.skill_set_category_id ", 
	    		  nativeQuery = true)
	    List<SkillSetSearchResultDAO> getSkillSetInfoDAOByInfoId(@Param("id") Long id); 
	   
	   @Transactional
	    @Query(value ="select s.id as id, s.skill_set_name as skillSetName,s.skill_set_descript as  skillSetDescript,s.username as username ,s.academic_level as academicLevel,s.tag as tag,c.category_name as skillCategoryName "
	    		+ "from skill_set_info s "
	    		+ ", skill_set_category c "
	    		+ "where s.username in ( :username ) and c.id = s.skill_set_category_id ", 
	    		  nativeQuery = true)
	    List<SkillSetSearchResultDAO> getSkillSetInfoDAOByUsername(@Param("username") String username);
	   
	   @Transactional
	    @Query(value ="select * from skill_set_info where username =:username ", 
	    		  nativeQuery = true)
	    List<SkillSetInfo> getSkillSetInfoByUserName(@Param("username")String user); 
	   
	
}

