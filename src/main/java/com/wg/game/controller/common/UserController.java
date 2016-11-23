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

import com.wg.game.dto.PageQueryRequest;
import com.wg.game.dto.PageQueryResult;
import com.wg.game.dto.ResultDataDto;
import com.wg.game.dtss.domain.user.User;
import com.wg.game.service.user.UserService;

@RestController
@RequestMapping({ "/user" })
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = {
			"/queryUsername" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDataDto<User> queryUserByUsername(String username) throws Exception {
		ResultDataDto<User> resultData = new ResultDataDto<User>();
		try {
			if (!StringUtils.isEmpty(username)) {

				resultData.setData(userService.findUserByUsername(username));

			} else {
				resultData.setRet("600");
				resultData.setMessage("参数为空");
			}

			return resultData;

		} catch (Exception e) {
			e.printStackTrace();
			resultData.setRet("201");
			resultData.setMessage("系统异常:" + e.getMessage());

			return resultData;
		}
	}

	@RequestMapping(value = {
			"/queryOpenid" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDataDto<User> queryUserByOpenid(String openid) throws Exception {
		ResultDataDto<User> resultData = new ResultDataDto<User>();
		try {
			if (!StringUtils.isEmpty(openid)) {

				resultData.setData(userService.findUserByOpenid(openid));

			} else {
				resultData.setRet("600");
				resultData.setMessage("参数为空");
			}

			return resultData;

		} catch (Exception e) {
			e.printStackTrace();
			resultData.setRet("201");
			resultData.setMessage("系统异常:" + e.getMessage());

			return resultData;
		}
	}

	@RequestMapping(value = { "/createUser" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDataDto<User> createUser(User user) {

		ResultDataDto<User> resultData = new ResultDataDto<User>();
		try {

			User dbUser = userService.findUserByUsername(user.getUsername());

			if (null != dbUser) {
				resultData.setRet("101");
				resultData.setMessage("用户已存在，请更换用户名");
				return resultData;
			}

			dbUser = userService.findUserByMobile(user.getMobile());

			if (null != dbUser) {
				resultData.setRet("102");
				resultData.setMessage("手机号已注册，请更手机号码");
				return resultData;
			}

			// 保存用户
			userService.saveUser(user);

			resultData.setData(user);
			return resultData;

		} catch (Exception e) {
			e.printStackTrace();

			resultData.setRet("201");
			resultData.setMessage("系统异常:" + e.getMessage());
			return resultData;
		}
	}
	
	
	@RequestMapping(value = { "/createUserOpenid" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDataDto<User> createUser(String openid) {

		ResultDataDto<User> resultData = new ResultDataDto<User>();
		try {

			User dbUser = userService.findUserByOpenid(openid);

			if (null != dbUser) {
				resultData.setRet("0");
				resultData.setMessage("用户已存在");
				resultData.setData(dbUser);
				return resultData;
			}

			//TODO 微信接口创建用户

			//resultData.setData(user);
			return resultData;

		} catch (Exception e) {
			e.printStackTrace();

			resultData.setRet("201");
			resultData.setMessage("系统异常:" + e.getMessage());
			return resultData;
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
