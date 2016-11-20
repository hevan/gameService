package com.wg.game.respository.common;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wg.game.domain.common.User;

@Repository
public  interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>
{
  @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username) ")
  public User findByUsername(@Param("username") String username);
  
  @Query("SELECT u FROM User u WHERE LOWER(u.mobile) = LOWER(:mobile) ")
  public User findByMobile(@Param("mobile") String mobile);
}