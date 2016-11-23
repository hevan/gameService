package com.wg.game.dtss.respository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wg.game.dtss.domain.common.GameRoom;

public interface GameRoomRepository extends JpaRepository<GameRoom, Long>, JpaSpecificationExecutor<GameRoom>{
	@Query("SELECT u FROM GameRoom u WHERE LOWER(u.roomNo) = LOWER(:roomNo) ")
	  public GameRoom findByRoomNo(@Param("roomNo") String roomNo);
	
	  @Query("SELECT u FROM GameRoom u WHERE u.user.id in :ids ")
	  public List<GameRoom> findAllByUsers(@Param("ids") List<Long> userIds);
}
