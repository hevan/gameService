package com.wg.game.respository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wg.game.domain.common.GameDishPlayer;

public interface GameDishPlayerRepository extends JpaRepository<GameDishPlayer, Long>, JpaSpecificationExecutor<GameDishPlayer>{

}
