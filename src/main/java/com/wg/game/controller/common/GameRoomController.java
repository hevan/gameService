package com.wg.game.controller.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wg.game.dto.ResultDataDto;
import com.wg.game.dtss.domain.common.GameRoom;
import com.wg.game.dtss.domain.common.GameRoomPlayer;
import com.wg.game.dtss.domain.user.User;
import com.wg.game.service.common.GameRoomPlayerService;
import com.wg.game.service.common.GameRoomService;
import com.wg.game.service.user.UserFollowService;
import com.wg.game.service.user.UserService;

@RestController
@RequestMapping("/gameRoom")
public class GameRoomController {

	
	@Autowired
	private GameRoomService gameRoomService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserFollowService userFollowService;
	
	@Autowired
	private GameRoomPlayerService gameRoomPlayerService;
	
	@RequestMapping(value = "/createGameRoom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDataDto<GameRoom> createRoom(GameRoom gameRoom) {

		ResultDataDto<GameRoom> resultData = new ResultDataDto<GameRoom>();
		try {

			//创建房间
			 gameRoomService.save(gameRoom);

			 //增加房间的玩家
			 GameRoomPlayer gameRoomPlayer = new GameRoomPlayer();
			 User dbUser = userService.findUser(gameRoom.getUser().getId());
			 gameRoomPlayer.setUser(dbUser);
			 gameRoomPlayer.setGameRoom(gameRoom);
			 
			 gameRoomPlayerService.save(gameRoomPlayer);

			resultData.setData(gameRoom);
			return resultData;

		} catch (Exception e) {
			e.printStackTrace();

			resultData.setRet("201");
			resultData.setMessage("系统异常:" + e.getMessage());
			return resultData;
		}
	}
	
	
	@RequestMapping(value = "/createGameRoomGet", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDataDto<GameRoom> createRoomGet(GameRoom gameRoom) {

		ResultDataDto<GameRoom> resultData = new ResultDataDto<GameRoom>();
		try {

			//创建房间
			 gameRoomService.save(gameRoom);

			 //增加房间的玩家
			 GameRoomPlayer gameRoomPlayer = new GameRoomPlayer();
			 User dbUser = userService.findUser(gameRoom.getUser().getId());
			 if(dbUser != null)
			 {
				 gameRoomPlayer.setUser(dbUser);
				 gameRoomPlayer.setGameRoom(gameRoom);
				 gameRoomPlayerService.save(gameRoomPlayer);
				resultData.setData(gameRoom);
				return resultData; 
			 }
			 else
			 {
				 resultData.setRet("201");
				resultData.setMessage("用户为空");
				return resultData;
			 }
	

		} catch (Exception e) {
			e.printStackTrace();

			resultData.setRet("201");
			resultData.setMessage("系统异常:" + e.getMessage());
			return resultData;
		}
	}
	
	//查询朋友房间
	@RequestMapping(value = "/friendsGameRoom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDataDto<List<GameRoom>> findFriendsRoom(Long userId) {

		ResultDataDto<List<GameRoom>> resultData = new ResultDataDto<List<GameRoom>>();
		try {

			//创建房间
			 List<Long> friendIds = userFollowService.findUserFriends(userId);
			 if(null != friendIds)
			 {
				 resultData.setData(gameRoomService.findByMyFriends(friendIds));
			 }
			
			return resultData;

		} catch (Exception e) {
			e.printStackTrace();

			resultData.setRet("201");
			resultData.setMessage("系统异常:" + e.getMessage());
			return resultData;
		}
	}
	
	
	
	
	//加入房间
	@RequestMapping(value = "/joinGameRoom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDataDto<GameRoomPlayer> joinGameRoom(String roomNo, Long userId) {

		ResultDataDto<GameRoomPlayer> resultData = new ResultDataDto<GameRoomPlayer>();
		try {

			 GameRoom gameRoom = gameRoomService.findByRoomNo(roomNo);
			 
			 if(null != gameRoom){
				 GameRoomPlayer gameRoomPlayer = new GameRoomPlayer();
				 User dbUser = userService.findUser(userId);
				 gameRoomPlayer.setGameRoom(gameRoom);
				 gameRoomPlayer.setUser(dbUser);
				 
				 gameRoomPlayerService.save(gameRoomPlayer);
				 resultData.setData(gameRoomPlayer);
			 }
			return resultData;

		} catch (Exception e) {
			e.printStackTrace();

			resultData.setRet("201");
			resultData.setMessage("系统异常:" + e.getMessage());
			return resultData;
		}
	}
	
	
	//加入房间
	@RequestMapping(value = "/joinGameRoomGet", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDataDto<GameRoomPlayer> joinGameRoomGet(String roomNo, Long userId) {

		ResultDataDto<GameRoomPlayer> resultData = new ResultDataDto<GameRoomPlayer>();
		try {

			 GameRoom gameRoom = gameRoomService.findByRoomNo(roomNo);
			 
			 if(null != gameRoom){
				 GameRoomPlayer gameRoomPlayer = new GameRoomPlayer();
				 User dbUser = userService.findUser(userId);
				 gameRoomPlayer.setGameRoom(gameRoom);
				 gameRoomPlayer.setUser(dbUser);
				 
				 gameRoomPlayerService.save(gameRoomPlayer);
				 resultData.setData(gameRoomPlayer);
			 }
			return resultData;

		} catch (Exception e) {
			e.printStackTrace();

			resultData.setRet("201");
			resultData.setMessage("系统异常:" + e.getMessage());
			return resultData;
		}
	}
	
	
}
