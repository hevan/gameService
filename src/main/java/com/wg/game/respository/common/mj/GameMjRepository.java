package com.wg.game.respository.common.mj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wg.game.domain.common.GameRoom;
import com.wg.game.domain.mj.GameMj;

public interface GameMjRepository extends JpaRepository<GameMj, Long>, JpaSpecificationExecutor<GameMj>{

	@Query("SELECT u FROM GameMj u WHERE u.name = :name ")
	  public GameMj findByName(@Param("name") String name);
}
