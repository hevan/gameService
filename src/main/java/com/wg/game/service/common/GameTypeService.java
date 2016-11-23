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

import com.wg.game.dtss.domain.common.GameType;
import com.wg.game.dtss.respository.common.GameTypeRepository;
import com.wg.game.utils.ModelUtils;


@Service
public class GameTypeService {
	private final Logger logger = LoggerFactory.getLogger(GameTypeService.class);

	@Autowired
	private GameTypeRepository gameTypeRepository;
	
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
	public Page<GameType> queryByPage(GameType gameType, Pageable pageable) throws Exception {
		try {
			return gameTypeRepository.findAll( new Specification<GameType>() {
				@Override
				public Predicate toPredicate(Root<GameType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> preList = new ArrayList<Predicate>();
					if (!StringUtils.isEmpty(gameType.getType())) {
						preList.add(cb.like(root.get("type").as(String.class), "%" + gameType.getType() + "%"));
					}

					if (!StringUtils.isEmpty(gameType.getName())) {
						preList.add(cb.like(root.get("name").as(String.class), "%" + gameType.getName() + "%"));
					}


					return query.where(cb.and(preList.toArray(new Predicate[preList.size()]))).getRestriction();
				}
			}, pageable);
		} catch (Exception e) {

			throw new RuntimeException(e.getMessage());

		}
	}

	@Transactional
	public void save(GameType gameType) throws Exception {

     try {
    	    //更新
			if(null != gameType.getId()){
				GameType dbModify = this.gameTypeRepository.findOne(gameType.getId());
				
				if (dbModify != null) {
					String[] ignoreProperties = ModelUtils.getIgnoreProperties(gameType);
					BeanUtils.copyProperties(gameType, dbModify, ignoreProperties);
					entityManager.merge(dbModify);
				}
			
			}else{
				//新增
				gameTypeRepository.save(gameType);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	
	public GameType findById(Long  id) throws Exception {

		return gameTypeRepository.findOne(id);
		
	}
	
	public GameType findByName(String  name) throws Exception {
		return gameTypeRepository.findByName(name);
	}

	
	@Transactional
	public void delete(Long id) throws Exception {
		gameTypeRepository.delete(id);
	}

}