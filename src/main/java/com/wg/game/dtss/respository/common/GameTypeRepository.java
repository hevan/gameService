package com.wg.game.dtss.respository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wg.game.dtss.domain.common.GameType;

public interface GameTypeRepository extends JpaRepository<GameType, Long>, JpaSpecificationExecutor<GameType>{

	@Query("SELECT u FROM GameType u WHERE u.name like :name ")
	  public List<GameType> findByName(@Param("name") String name);
}
