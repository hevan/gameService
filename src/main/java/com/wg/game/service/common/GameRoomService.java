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
import org.springframework.util.StringUtils;

import com.wg.game.dtss.domain.common.GameRoom;
import com.wg.game.dtss.respository.common.GameRoomRepository;
import com.wg.game.dtss.respository.user.UserFollowRepository;
import com.wg.game.utils.ModelUtils;


@Service
public class GameRoomService {
	private final Logger logger = LoggerFactory.getLogger(GameRoomService.class);

	@Autowired
	private GameRoomRepository gameRoomRepository;
	
	@Autowired
	private UserFollowRepository userFollowRepository;
	
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
	public Page<GameRoom> queryByPage(GameRoom gameRoom, List<Long> listFollowIds, Pageable pageable) throws Exception {
		try {
		
			return gameRoomRepository.findAll( new Specification<GameRoom>() {
				@Override
				public Predicate toPredicate(Root<GameRoom> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> preList = new ArrayList<Predicate>();
					if (!StringUtils.isEmpty(gameRoom.getRoomNo())) {
						preList.add(cb.like(root.get("roomNo").as(String.class), "%" + gameRoom.getRoomNo() + "%"));
					}
					
					if (null != listFollowIds && listFollowIds.size() > 0) {
						preList.add(root.join("followedUser").get("id").in(listFollowIds));
					}

					return query.where(cb.and(preList.toArray(new Predicate[preList.size()]))).getRestriction();
				}
			}, pageable);
		} catch (Exception e) {

			throw new RuntimeException(e.getMessage());

		}
	}

	@Transactional
	public void save(GameRoom gameRoom) throws Exception {

     try {
    	    //更新
			if(null != gameRoom.getId()){
				GameRoom dbModify = this.gameRoomRepository.findOne(gameRoom.getId());
				
				if (dbModify != null) {
					String[] ignoreProperties = ModelUtils.getIgnoreProperties(gameRoom);
					BeanUtils.copyProperties(gameRoom, dbModify, ignoreProperties);
					entityManager.merge(dbModify);
				}
			
			}else{
				//新增
				gameRoomRepository.save(gameRoom);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	public GameRoom findById(Long  id) throws Exception {
		return gameRoomRepository.findOne(id);
	}
	
	public GameRoom findByRoomNo(String  roomNo) throws Exception {
		return gameRoomRepository.findByRoomNo(roomNo);
	}

	
	public List<GameRoom> findByMyFriends(List<Long>  friendIds) throws Exception {
		return gameRoomRepository.findAllByUsers(friendIds);
	}
	
	
	@Transactional
	public void delete(Long id) throws Exception {
		gameRoomRepository.delete(id);
	}
	
	

}