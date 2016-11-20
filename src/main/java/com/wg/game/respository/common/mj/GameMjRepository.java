package com.wg.game.respository.common.mj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.wg.game.domain.mj.GameMj;

public interface GameMjRepository extends JpaRepository<GameMj, Long>, JpaSpecificationExecutor<GameMj>{

}
