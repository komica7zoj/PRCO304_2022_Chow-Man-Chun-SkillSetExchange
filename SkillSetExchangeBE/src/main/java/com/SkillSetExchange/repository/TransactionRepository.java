package com.SkillSetExchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SkillSetExchange.models.TransactionInfo;

@Repository
public interface TransactionRepository  extends JpaRepository<TransactionInfo, Long> {

	
}
