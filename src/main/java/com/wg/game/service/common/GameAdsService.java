package com.wg.game.service.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.wg.game.dtss.domain.common.GameAds;
import com.wg.game.dtss.domain.common.GameDishPlayer;
import com.wg.game.dtss.domain.common.GameType;
import com.wg.game.dtss.respository.common.GameAdsRepository;
import com.wg.game.utils.ModelUtils;

public class GameAdsService {
	private final Logger logger = LoggerFactory.getLogger(GameAdsService.class);

	@Autowired
	GameAdsRepository gameAdsRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public Page<GameAds> queryByPage(GameAds gameAds, Long gameTypeId, Pageable pageable) throws Exception {
		try {
		
			return gameAdsRepository.findAll( new Specification<GameAds>() {
				@Override
				public Predicate toPredicate(Root<GameAds> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> preList = new ArrayList<Predicate>();
					if (null != gameAds.getName()) {
						preList.add(cb.like(root.get("name").as(String.class), gameAds.getName()));
					}
					
					if (null != gameTypeId) {
						
						SetJoin<GameAds,GameType> depJoin = root.join(root.getModel().getSet("gameTypes",GameType.class) , JoinType.LEFT);
						
						preList.add(cb.equal(depJoin.get("id").as(Long.class),gameTypeId));
					}
					
					return query.where(cb.and(preList.toArray(new Predicate[preList.size()]))).getRestriction();
				}
			}, pageable);
		} catch (Exception e) {

			throw new RuntimeException(e.getMessage());

		}
	}

	@Transactional
	public void save(GameAds gameAds) throws Exception {

     try {
    	    //更新
			if(null != gameAds.getId()){
				GameAds dbModify = this.gameAdsRepository.findOne(gameAds.getId());
				
				if (dbModify != null) {
					String[] ignoreProperties = ModelUtils.getIgnoreProperties(gameAds);
					BeanUtils.copyProperties(gameAds, dbModify, ignoreProperties);
					entityManager.merge(dbModify);
				}
			
			}else{
				//新增
				gameAdsRepository.save(gameAds);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	public GameAds findById(Long  id) throws Exception {
		return gameAdsRepository.findOne(id);
	}
	
	public List<GameAds> findByGameTypeId(Long  gameTypeId) throws Exception {
		return gameAdsRepository.findByGameType(gameTypeId);
	}

	@Transactional
	public void delete(Long id) throws Exception {
		gameAdsRepository.delete(id);
	}
	
}
