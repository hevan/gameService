package com.wg.game.respository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wg.game.domain.common.GameRoomPlayer;

public interface GameRoopPlayerRepository extends JpaRepository<GameRoomPlayer, Long>, JpaSpecificationExecutor<GameRoomPlayer>{

}
