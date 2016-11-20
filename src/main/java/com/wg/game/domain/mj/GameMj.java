package com.wg.game.domain.mj;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wg.game.domain.common.GameType;

@Entity
@Table(name = "game_mj")
public class GameMj implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "game_type_id")
	private GameType gameType;
	
	private Integer dishUserMax; //玩家最大人数
	
	private Integer cardCount; //牌数

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public Integer getDishUserMax() {
		return dishUserMax;
	}

	public void setDishUserMax(Integer dishUserMax) {
		this.dishUserMax = dishUserMax;
	}

	public Integer getCardCount() {
		return cardCount;
	}

	public void setCardCount(Integer cardCount) {
		this.cardCount = cardCount;
	}
	
	
	
}
