package com.wg.game.dtss.domain.common;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wg.game.dtss.domain.user.User;

@Entity
@Table(name = "game_room")
public class GameRoom implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//房间号
	@Column(length = 20)
	private String roomNo;
	
	//游戏类型-外键
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "game_type_id")
	@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler", "fieldHandler"})
	private GameType gameType;
	
	//用户ID-外键
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler", "fieldHandler"})
	private User user;
	
	//游戏盘数
	private Integer gameDish; 
	
	//基础积分 5块，10块
	private Integer baseScore; 
	
	//消耗金币数
	private BigDecimal usedGold; 
	
	//状态信息
	@Embedded
	private BeanStatus beanStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BeanStatus getBeanStatus() {
		return beanStatus;
	}

	public void setBeanStatus(BeanStatus beanStatus) {
		this.beanStatus = beanStatus;
	}

	public Integer getGameDish() {
		return gameDish;
	}

	public void setGameDish(Integer gameDish) {
		this.gameDish = gameDish;
	}

	public BigDecimal getUsedGold() {
		return usedGold;
	}

	public void setUsedGold(BigDecimal usedGold) {
		this.usedGold = usedGold;
	}

	public Integer getBaseScore() {
		return baseScore;
	}

	public void setBaseScore(Integer baseScore) {
		this.baseScore = baseScore;
	}
}
