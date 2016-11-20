package com.wg.game.respository.common.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wg.game.domain.user.UserFollow;

public interface UserFollowRepository extends JpaRepository<UserFollow, Long>, JpaSpecificationExecutor<UserFollow>{

	@Query("SELECT u FROM UserFollow u WHERE u.userId =:userId ")
	List<UserFollow> findAllFollow(@Param("userId")  Long userId);
	
	@Query("SELECT u.followUser.id FROM UserFollow u WHERE u.userId =:userId ")
	List<Long> findAllFollowId(@Param("userId")  Long userId);
}
