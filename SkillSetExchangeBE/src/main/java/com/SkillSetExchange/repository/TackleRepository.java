package com.SkillSetExchange.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SkillSetExchange.models.SkillSetType;
import com.SkillSetExchange.models.TackleInfo;
import com.SkillSetExchange.repository.dao.TackleViewDAO;


@Repository
public interface TackleRepository extends JpaRepository<TackleInfo, Long>{

    @Transactional
    @Query(value ="select ti.id as id,"
    		+ "ti.username as username,"
    		+ "ti.tackle_name as tackleName,"
    		+ "sc.category_name as skillCategoryName,"
    		+ "ti.created_date as createDate,"
    		+ "ti.tackle_skill_set_info_id as tackleSkillSetInfoId,"
    		+ "ti.skill_set_info_id as skillSetInfoId,"
    		+ "(select academic_level from skill_set_info where id = ti.skill_set_info_id) as academicLevel,"
    		+ "(select skill_set_name from skill_set_info where id = ti.tackle_skill_set_info_id) as tackleSkillSetName,"
    		+ "(select skill_set_name from skill_set_info where id = ti.skill_set_info_id) as skillSetName,"
    		+ "ti.transaction_info_id as transactionInfoId "
    		+ "from tackle_info ti join skill_set_category sc on(ti.skill_set_category_id = sc.id) "
    		+ "where ti.username = :username and ti.status in ('S')", 
    		  nativeQuery = true)
    List<TackleViewDAO> getTackleByUsername(@Param("username") String username);
    
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into tackle_info (id,username,created_date,skill_set_category_id,tackle_name,skill_set_info_id, tackle_skill_set_info_id,tackle_descript,transaction_info_id,status,status_type,tag) "
            + "VALUES (nextval('tackle_info_id_seq'),:#{#t.username},  :#{#t.createDate} ,:#{#t.skillSetCategoryId}, :#{#t.tackleName}, :#{#t.skillSetInfoId},case when :#{#t.tackleSkillSetInfoId} = 0 then null else :#{#t.tackleSkillSetInfoId} end, :#{#t.tackleDescript}, null, :#{#t.status}, :#{#t.statusType}, :#{#t.tag} )", 
	  nativeQuery = true)
    public int newTackle(@Param("t")  TackleInfo tackleInfo);

    @Transactional
    @Query(value ="select ti.id as id,"
    		+ "ti.username as username,"
    		+ "ti.tackle_name as tackleName,"
    		+ "sc.category_name as skillCategoryName,"
    		+ "ti.created_date as createDate,"
    		+ "ti.tackle_skill_set_info_id as tackleSkillSetInfoId,"
    		+ "ti.skill_set_info_id as skillSetInfoId,"
    		+ "(select academic_level from skill_set_info where id = ti.skill_set_info_id) as academicLevel,"
    		+ "(select skill_set_name from skill_set_info where id = ti.tackle_skill_set_info_id) as tackleSkillSetName,"
    		+ "(select skill_set_name from skill_set_info where id = ti.skill_set_info_id) as skillSetName,"
    		+ "ti.transaction_info_id as transactionInfoId "
    		+ "from tackle_info ti join skill_set_category sc on(ti.skill_set_category_id = sc.id) "
    		+ "where ti.tackle_name = :tackleName and ti.status in ('S')", 
    		  nativeQuery = true)
    List<TackleViewDAO> getTackleByTacklename(@Param("tackleName") String tackleName);
    
   
}
