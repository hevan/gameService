package com.wg.game.respository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wg.game.domain.common.GameRoom;

public interface GameRoomRepository extends JpaRepository<GameRoom, Long>, JpaSpecificationExecutor<GameRoom>{
	@Query("SELECT u FROM GameRoom u WHERE LOWER(u.roomNo) = LOWER(:roomNo) ")
	  public GameRoom findByRoomNo(@Param("roomNo") String roomNo);
}
