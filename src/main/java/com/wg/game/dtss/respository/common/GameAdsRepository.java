package com.wg.game.dtss.respository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wg.game.dtss.domain.common.GameAds;

public interface GameAdsRepository extends JpaRepository<GameAds, Long>, JpaSpecificationExecutor<GameAds>{
   
	@Query("SELECT distinct u FROM GameAds u join u.gameTypes  cat  WHERE cat.id = :gameTypeId")
	List<GameAds> findByGameType(@Param("gameTypeId") Long gameTypeId);
}
