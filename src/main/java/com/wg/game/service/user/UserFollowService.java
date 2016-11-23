package com.wg.game.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wg.game.dtss.domain.user.User;
import com.wg.game.dtss.domain.user.UserFollow;
import com.wg.game.dtss.respository.user.UserFollowRepository;
import com.wg.game.dtss.respository.user.UserRepository;

@Service
public class UserFollowService {
	
	
	@Autowired
	UserFollowRepository userFollowRepository;
	
	@Autowired
	UserRepository userRepository;
	
	//查找我关注的人
	public List<Long> findUserFriends(Long userId){
		return userFollowRepository.findAllFollowId(userId);
	}
	
	
	public UserFollow  followFriend(Long userId,Long friendId){
		
		User friends = userRepository.findOne(friendId);
		
		UserFollow userFollow = userFollowRepository.findUserFollowByFriendId(userId,friendId);
		
		if(null != userFollow){
			return userFollow;
		}
			
		if(null != friends){
		    userFollow = new UserFollow();
			userFollow.setFollowedUser(friends);
			userFollow.setUserId(userId);
			
			userFollowRepository.save(userFollow);
			
			return userFollow;
		}
		
		return  null;
	}
	
}
