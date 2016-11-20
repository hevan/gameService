package com.wg.game.service.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wg.game.domain.common.User;
import com.wg.game.respository.common.UserRepository;


@Service
public class UserService {
	private final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	/**
	 * 创建会员分页
	 * 
	 * @param user
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Page<User> queryUserByPage(User user, Pageable pageable) throws Exception {
		try {
			return userRepository.findAll( new Specification<User>() {
				@Override
				public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					List<Predicate> preList = new ArrayList<Predicate>();
					if (!StringUtils.isEmpty(user.getUsername())) {
						preList.add(cb.like(root.get("username").as(String.class), "%" + user.getUsername() + "%"));
					}

					if (!StringUtils.isEmpty(user.getMobile())) {
						preList.add(cb.like(root.get("mobile").as(String.class), "%" + user.getMobile() + "%"));
					}

					if (!StringUtils.isEmpty(user.getCity())) {
						preList.add(cb.like(root.get("city").as(String.class), "%" + user.getCity() + "%"));
					}

					return query.where(cb.and(preList.toArray(new Predicate[preList.size()]))).getRestriction();
				}
			}, pageable);
		} catch (Exception e) {

			throw new RuntimeException(e.getMessage());

		}
	}

	@Transactional
	public void saveUser(User user) throws Exception {

		userRepository.save(user);

	}
	
	
	public User findUserByUsername(String  username) throws Exception {

		return userRepository.findByUsername(username);
		
	}
	
	public User findUserByMobile(String  mobile) throws Exception {

		return userRepository.findByMobile(mobile);
		
	}
	
	@Transactional
	public void deleteUser(Long id) throws Exception {
		userRepository.delete(id);
	}

}