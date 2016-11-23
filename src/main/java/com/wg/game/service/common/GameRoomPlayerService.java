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

import com.wg.game.dtss.domain.common.GameRoom;
import com.wg.game.dtss.domain.common.GameRoomPlayer;
import com.wg.game.dtss.domain.user.User;
import com.wg.game.dtss.respository.common.GameRoomPlayerRepository;
import com.wg.game.dtss.respository.common.GameRoomRepository;
import com.wg.game.dtss.respository.user.UserRepository;
import com.wg.game.utils.ModelUtils;


@Service
public class GameRoomPlayerService {
	private final Logger logger = LoggerFactory.getLogger(GameRoomPlayerService.class);

	@Autowired
	private GameRoomPlayerRepository gameRoomPlayerRepository;
	
	@Autowired
	private GameRoomRepository gameRoomRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
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
	public Page<GameRoomPlayer> queryByPage(GameRoomPlayer gameRoomPlayer, Pageable pageable) throws Exception {
		try {
		
			return gameRoomPlayerRepository.findAll( new Specification<GameRoomPlayer>() {
				@Override
				public Predicate toPredicate(Root<GameRoomPlayer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> preList = new ArrayList<Predicate>();
					if (null != gameRoomPlayer.getGameRoom()) {
						preList.add(cb.equal(root.get("gameRoom").as(GameRoom.class), gameRoomPlayer.getGameRoom()));
					}
					return query.where(cb.and(preList.toArray(new Predicate[preList.size()]))).getRestriction();
				}
			}, pageable);
		} catch (Exception e) {

			throw new RuntimeException(e.getMessage());

		}
	}

	@Transactional
	public void save(GameRoomPlayer gameRoomPlayer) throws Exception {

     try {
    	    //更新
			if(null != gameRoomPlayer.getId()){
				GameRoomPlayer dbModify = this.gameRoomPlayerRepository.findOne(gameRoomPlayer.getId());
				
				if (dbModify != null) {
					String[] ignoreProperties = ModelUtils.getIgnoreProperties(gameRoomPlayer);
					BeanUtils.copyProperties(gameRoomPlayer, dbModify, ignoreProperties);
					entityManager.merge(dbModify);
				}
			
			}else{
				//新增
				gameRoomPlayerRepository.save(gameRoomPlayer);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	public GameRoomPlayer findById(Long  id) throws Exception {
		return gameRoomPlayerRepository.findOne(id);
	}
	
	public List<GameRoomPlayer> findByRoomNo(String  roomNo) throws Exception {
		return gameRoomPlayerRepository.findByRoomNo(roomNo);
	}

	
	
	public Long joinGamePlayer(String roomNo, Long userId) throws Exception {
		 GameRoom gameRoom = gameRoomRepository.findByRoomNo(roomNo);
		 
		 if(null != gameRoom){
			 GameRoomPlayer gameRoomPlayer = new GameRoomPlayer();
			 User dbUser = userRepository.findOne(userId);
			 gameRoomPlayer.setGameRoom(gameRoom);
			 gameRoomPlayer.setUser(dbUser);
			 
			 gameRoomPlayerRepository.save(gameRoomPlayer); 
			 return gameRoomPlayer.getId();
		 }
		 return null;
	}
	
	
	@Transactional
	public void delete(Long id) throws Exception {
		gameRoomPlayerRepository.delete(id);
	}
}