package com.wg.game.respository.common.user;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wg.game.domain.user.User;

@Repository
public  interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>
{
  @Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username) ")
  public User findByUsername(@Param("username") String username);
  
  @Query("SELECT u FROM User u WHERE LOWER(u.mobile) = LOWER(:mobile) ")
  public User findByMobile(@Param("mobile") String mobile);
  
  @Query("SELECT u FROM User u WHERE u.openid = :openid")
  public User findByOpenid(@Param("openid") String openid);
}