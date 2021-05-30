package com.SkillSetExchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SkillSetExchange.models.TackleInfo;
import com.SkillSetExchange.models.TransactionInfo;

@Repository
public interface TransactionRepository  extends JpaRepository<TransactionInfo, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update transaction_info set status = :#{#t.status}, transaction_hash = :#{#t.transactionHash} where id = :#{#t.id}", 
	  nativeQuery = true)
    public int updateTransaction(@Param("t")  TransactionInfo transactionInfo);

}
