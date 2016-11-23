package com.wg.game.service.mj;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wg.game.dtss.domain.mj.GameMj;
import com.wg.game.dtss.respository.mj.GameMjRepository;
import com.wg.game.utils.ModelUtils;

@Service
public class GameMjService {

	private final Logger logger = LoggerFactory.getLogger(GameMjService.class);

	@Autowired
	private GameMjRepository gameMjRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(GameMj gameType) throws Exception {

     try {
    	    //更新
			if(null != gameType.getId()){
				GameMj dbModify = this.gameMjRepository.findOne(gameType.getId());
				
				if (dbModify != null) {
					String[] ignoreProperties = ModelUtils.getIgnoreProperties(gameType);
					BeanUtils.copyProperties(gameType, dbModify, ignoreProperties);
					entityManager.merge(dbModify);
				}
			
			}else{
				//新增
				gameMjRepository.save(gameType);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	
	public GameMj findById(Long  id) throws Exception {

		return gameMjRepository.findOne(id);
		
	}
	
	

	
	@Transactional
	public void delete(Long id) throws Exception {
		gameMjRepository.delete(id);
	}
}
