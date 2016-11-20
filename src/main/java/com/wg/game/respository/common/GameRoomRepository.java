package com.wg.game.respository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wg.game.domain.common.GameRoom;

public interface GameRoomRepository extends JpaRepository<GameRoom, Long>, JpaSpecificationExecutor<GameRoom>{

}
