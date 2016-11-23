package com.wg.game.dtss.respository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wg.game.dtss.domain.common.GameRoomPlayer;

public interface GameRoomPlayerRepository extends JpaRepository<GameRoomPlayer, Long>, JpaSpecificationExecutor<GameRoomPlayer>{

	@Query("SELECT u FROM GameRoomPlayer u WHERE u.gameRoom.roomNo =:roomNo")
	List<GameRoomPlayer> findByRoomNo(@Param("roomNo") String roomNo);
	
}
