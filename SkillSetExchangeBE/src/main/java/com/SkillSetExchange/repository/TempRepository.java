package com.SkillSetExchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.SkillSetExchange.models.tempVariable;
import com.SkillSetExchange.repository.dao.TempDAO;
@Repository
public interface TempRepository extends JpaRepository<tempVariable, Long>{

	@Query(value = "CALL get_chain_pw(:o_chainPW);", nativeQuery = true)
	List<TempDAO> getChainPW(@Param("o_chainPW") String o_chainPW);
}
