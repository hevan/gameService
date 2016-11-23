package com.wg.game.controller.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wg.game.dto.ResultDataDto;
import com.wg.game.dtss.domain.common.GameAds;
import com.wg.game.dtss.domain.common.GameType;
import com.wg.game.service.common.GameAdsService;
import com.wg.game.service.common.GameTypeService;

@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameTypeService gameTypeService;
	
	@Autowired
	private GameAdsService gameAdsService;

	@RequestMapping(value = {
			"/getGameType" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDataDto<List<GameType>> queryByGameName(String name) throws Exception {
		ResultDataDto<List<GameType>> resultData = new ResultDataDto<List<GameType>>();
		try {
			if (!StringUtils.isEmpty(name)) {

				resultData.setData(gameTypeService.findByName(name));

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
			"/getGameAds" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResultDataDto<List<GameAds>> queryByGameAds(Long gameTypeId) throws Exception {
		ResultDataDto<List<GameAds>> resultData = new ResultDataDto<List<GameAds>>();
		try {
			if (null != gameTypeId) {

				resultData.setData(gameAdsService.findByGameTypeId(gameTypeId));

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

}
