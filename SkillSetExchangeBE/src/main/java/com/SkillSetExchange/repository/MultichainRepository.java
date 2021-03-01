package com.SkillSetExchange.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.SkillSetExchange.models.UserMultichainContent;


@Repository
public interface MultichainRepository extends JpaRepository<UserMultichainContent,Long>  {
    //List<UserMultichainContent> findByType(String type, Pageable request);

    @Transactional
    @Query(value ="select * from user_multichain_content where username = :username", 
    		  nativeQuery = true)
    UserMultichainContent getUserMultichainContent(@Param("username") String username);
}
