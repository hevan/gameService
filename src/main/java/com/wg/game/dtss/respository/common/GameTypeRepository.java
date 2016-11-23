package com.wg.game.dtss.respository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wg.game.dtss.domain.common.GameType;
import com.wg.game.dtss.domain.user.User;

public interface GameTypeRepository extends JpaRepository<GameType, Long>, JpaSpecificationExecutor<GameType>{

	@Query("SELECT u FROM GameType u WHERE LOWER(u.name) = LOWER(:name) ")
	  public GameType findByName(@Param("name") String name);
}
