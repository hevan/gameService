package com.wg.game.dtss.respository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wg.game.dtss.domain.common.GameDish;
import com.wg.game.dtss.domain.common.GameRoomPlayer;


public interface GameDishRepository extends JpaRepository<GameDish, Long>, JpaSpecificationExecutor<GameDish>{

	@Query("SELECT u FROM GameDish u WHERE u.gameRoom.roomNo =:roomNo")
	List<GameDish> findByRoomNo(@Param("roomNo") String roomNo);
}
