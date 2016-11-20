package com.wg.game.respository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wg.game.domain.common.GameType;

public interface GameTypeRepository extends JpaRepository<GameType, Long>, JpaSpecificationExecutor<GameType>{

}
