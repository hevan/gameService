package com.wg.game.dtss.respository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wg.game.dtss.domain.common.GameDishPlayer;

public interface GameDishPlayerRepository extends JpaRepository<GameDishPlayer, Long>, JpaSpecificationExecutor<GameDishPlayer>{

	@Query("SELECT u FROM GameDishPlayer u WHERE u.gameDish.id =:dishId")
	List<GameDishPlayer> findByDishId(@Param("dishId") Long dishId);
}
