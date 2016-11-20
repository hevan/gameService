package com.wg.game.respository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wg.game.domain.common.GameDish;


public interface GameDishRepository extends JpaRepository<GameDish, Long>, JpaSpecificationExecutor<GameDish>{

}
