package com.wg.game.service.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wg.game.dtss.domain.common.GameDish;
import com.wg.game.dtss.domain.common.GameDishPlayer;
import com.wg.game.dtss.respository.common.GameDishPlayerRepository;
import com.wg.game.utils.ModelUtils;


@Service
public class GameDishPlayerService {
	private final Logger logger = LoggerFactory.getLogger(GameDishPlayerService.class);

	@Autowired
	private GameDishPlayerRepository gameDishPlayerRepository;
	
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 创建会员分页
	 * 
	 * @param user
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Page<GameDishPlayer> queryByPage(GameDishPlayer gameDishPlayer, Pageable pageable) throws Exception {
		try {
		
			return gameDishPlayerRepository.findAll( new Specification<GameDishPlayer>() {
				@Override
				public Predicate toPredicate(Root<GameDishPlayer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> preList = new ArrayList<Predicate>();
					if (null != gameDishPlayer.getGameDish()) {
						preList.add(cb.equal(root.get("gameDish").as(GameDish.class), gameDishPlayer.getGameDish()));
					}
					return query.where(cb.and(preList.toArray(new Predicate[preList.size()]))).getRestriction();
				}
			}, pageable);
		} catch (Exception e) {

			throw new RuntimeException(e.getMessage());

		}
	}

	@Transactional
	public void save(GameDishPlayer gameDishPlayer) throws Exception {

     try {
    	    //更新
			if(null != gameDishPlayer.getId()){
				GameDishPlayer dbModify = this.gameDishPlayerRepository.findOne(gameDishPlayer.getId());
				
				if (dbModify != null) {
					String[] ignoreProperties = ModelUtils.getIgnoreProperties(gameDishPlayer);
					BeanUtils.copyProperties(gameDishPlayer, dbModify, ignoreProperties);
					entityManager.merge(dbModify);
				}
			
			}else{
				//新增
				gameDishPlayerRepository.save(gameDishPlayer);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	public GameDishPlayer findById(Long  id) throws Exception {
		return gameDishPlayerRepository.findOne(id);
	}
	
	public List<GameDishPlayer> findByDishId(Long  dishId) throws Exception {
		return gameDishPlayerRepository.findByDishId(dishId);
	}

	@Transactional
	public void delete(Long id) throws Exception {
		gameDishPlayerRepository.delete(id);
	}
}