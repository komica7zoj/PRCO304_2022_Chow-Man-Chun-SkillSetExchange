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


@Repository
public interface TackleRepository extends JpaRepository<TackleInfo, Long>{

    @Transactional
    @Query(value ="select * from tackle_info where username = :username", 
    		  nativeQuery = true)
    List<TackleInfo> getTackleByUsername();
    
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into tackle_info (id,username,created_date,skill_set_category_id,tackle_name,tackle_descript,transaction_info_id,status,tag) "
            + "VALUES (nextval('tackle_info_id_seq'),:#{#t.username},  :#{#t.createDate} ,:#{#t.skillSetCategoryId}, :#{#t.tackleName}, :#{#t.tackleDescript}, null, :#{#t.status}, :#{#t.tag} )", 
	  nativeQuery = true)
    public int newTackle(@Param("t")  TackleInfo tackleInfo);

    
}
