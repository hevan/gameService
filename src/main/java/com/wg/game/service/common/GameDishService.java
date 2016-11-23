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
import com.wg.game.dtss.domain.common.GameRoom;
import com.wg.game.dtss.respository.common.GameDishRepository;
import com.wg.game.utils.ModelUtils;


@Service
public class GameDishService {
	private final Logger logger = LoggerFactory.getLogger(GameDishService.class);

	@Autowired
	private GameDishRepository gameDishRepository;
	

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
	public Page<GameDish> queryByPage(GameDish gameDish, Pageable pageable) throws Exception {
		try {
		
			return gameDishRepository.findAll( new Specification<GameDish>() {
				@Override
				public Predicate toPredicate(Root<GameDish> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> preList = new ArrayList<Predicate>();
					if (null != gameDish.getGameRoom()) {
						preList.add(cb.equal(root.get("gameRoom").as(GameRoom.class), gameDish.getGameRoom()));
					}
					
					return query.where(cb.and(preList.toArray(new Predicate[preList.size()]))).getRestriction();
				}
			}, pageable);
		} catch (Exception e) {

			throw new RuntimeException(e.getMessage());

		}
	}

	@Transactional
	public void save(GameDish gameDish) throws Exception {

     try {
    	    //更新
			if(null != gameDish.getId()){
				GameDish dbModify = this.gameDishRepository.findOne(gameDish.getId());
				
				if (dbModify != null) {
					String[] ignoreProperties = ModelUtils.getIgnoreProperties(gameDish);
					BeanUtils.copyProperties(gameDish, dbModify, ignoreProperties);
					entityManager.merge(dbModify);
				}
			
			}else{
				//新增
				gameDishRepository.save(gameDish);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	public GameDish findById(Long  id) throws Exception {
		return gameDishRepository.findOne(id);
	}
	
	public List<GameDish> findByRoomNo(String  roomNo) throws Exception {
		return gameDishRepository.findByRoomNo(roomNo);
	}

	@Transactional
	public void delete(Long id) throws Exception {
		gameDishRepository.delete(id);
	}

}