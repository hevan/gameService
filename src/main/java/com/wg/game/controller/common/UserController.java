package com.wg.game.controller.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wg.game.domain.user.User;
import com.wg.game.dto.PageQueryRequest;
import com.wg.game.dto.PageQueryResult;
import com.wg.game.service.user.UserService;

@RestController
@RequestMapping({ "/user" })
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = {
			"/findUserByUsername" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public User findUserByUsername(String username) throws Exception {

		try {
			if (!StringUtils.isEmpty(username)) {
				return userService.findUserByUsername(username);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = {
			"/save" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> saveUser(User user) {

		Map<String, String> retMap = new HashMap<String, String>();
		try {
			
			User dbUser = userService.findUserByUsername(user.getUsername());
			
			if(null != dbUser){
				retMap.put("success", "false");
				retMap.put("code", "201");
				retMap.put("message", "用户已存在，请更换用户名");
				
				return retMap;
			}
			
			dbUser = userService.findUserByMobile(user.getMobile());
			
			if(null != dbUser){
				retMap.put("success", "false");
				retMap.put("code", "301");
				retMap.put("message", "用户手机号已存在，请更换");
				
				return retMap;
			}
			
			
			//保存用户
			userService.saveUser(user);

			retMap.put("success", "true");
			retMap.put("code", "200");
			retMap.put("message", "用户创建成功");
			return retMap;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			retMap.put("success", "false");
			retMap.put("code", "401");
			retMap.put("message", "用户创建失败:" + e.getMessage());
			return retMap;
		}
	}
	
	@RequestMapping(value = "/pageUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PageQueryResult<User> pageQueryUsers(User user, PageQueryRequest pageRequest) throws Exception {
		try {
			Page<User> pageResult = userService.queryUserByPage(user, new PageRequest(pageRequest.getCurrentPage() - 1,
					pageRequest.getPageSize(), new Sort(Sort.Direction.ASC, "id")));

			PageQueryResult<User> result = new PageQueryResult<User>();
			result.setData(pageResult.getContent());
			result.setiTotalRecords(pageResult.getTotalElements());
			result.setiTotalDisplayRecords(pageResult.getTotalElements());
			result.setSuccess(true);

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
