package com.wg.game.domain.common;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "game_dish")
public class GameDish implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "game_room_id")
	private GameRoom gameRoom;
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User userWinner;
	
	private Integer dishNo; //第几盘
	
    private Integer act; //胡牌数
	
	private Integer actScore;//胡牌积分

	private Integer actOne; //动作数1
	
	private Integer actOneScore;//动作数1积分
	
    private Integer act1;//动作数2
	
	private Integer act2Score;////动作数2积分
	
	private Integer act3;//动作数3
		
    private Integer act3Score;////动作数3积分
    
    private Integer act4;//动作数4
	
    private Integer act4Score;////动作数4积分
    
    private Integer act5;//动作数4
	
    private Integer act5Score;////动作数4积分
    
    private Integer act6;//动作数4
	
    private Integer act6Score;////动作数4积分
    
    @Embedded
	private BeanStatus beanStatus;//状态信息

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GameRoom getGameRoom() {
		return gameRoom;
	}

	public void setGameRoom(GameRoom gameRoom) {
		this.gameRoom = gameRoom;
	}

	public User getUserWinner() {
		return userWinner;
	}

	public void setUserWinner(User userWinner) {
		this.userWinner = userWinner;
	}
	
	public Integer getAct() {
		return act;
	}

	public void setAct(Integer act) {
		this.act = act;
	}

	public Integer getActScore() {
		return actScore;
	}

	public void setActScore(Integer actScore) {
		this.actScore = actScore;
	}

	public Integer getActOne() {
		return actOne;
	}

	public void setActOne(Integer actOne) {
		this.actOne = actOne;
	}

	public Integer getActOneScore() {
		return actOneScore;
	}

	public void setActOneScore(Integer actOneScore) {
		this.actOneScore = actOneScore;
	}

	public Integer getAct1() {
		return act1;
	}

	public void setAct1(Integer act1) {
		this.act1 = act1;
	}

	public Integer getAct2Score() {
		return act2Score;
	}

	public void setAct2Score(Integer act2Score) {
		this.act2Score = act2Score;
	}

	public Integer getAct3() {
		return act3;
	}

	public void setAct3(Integer act3) {
		this.act3 = act3;
	}

	public Integer getAct3Score() {
		return act3Score;
	}

	public void setAct3Score(Integer act3Score) {
		this.act3Score = act3Score;
	}

	public Integer getAct4() {
		return act4;
	}

	public void setAct4(Integer act4) {
		this.act4 = act4;
	}

	public Integer getAct4Score() {
		return act4Score;
	}

	public void setAct4Score(Integer act4Score) {
		this.act4Score = act4Score;
	}

	public Integer getAct5() {
		return act5;
	}

	public void setAct5(Integer act5) {
		this.act5 = act5;
	}

	public Integer getAct5Score() {
		return act5Score;
	}

	public void setAct5Score(Integer act5Score) {
		this.act5Score = act5Score;
	}

	public Integer getAct6() {
		return act6;
	}

	public void setAct6(Integer act6) {
		this.act6 = act6;
	}

	public Integer getAct6Score() {
		return act6Score;
	}

	public void setAct6Score(Integer act6Score) {
		this.act6Score = act6Score;
	}

	public BeanStatus getBeanStatus() {
		return beanStatus;
	}

	public void setBeanStatus(BeanStatus beanStatus) {
		this.beanStatus = beanStatus;
	}

	public Integer getDishNo() {
		return dishNo;
	}

	public void setDishNo(Integer dishNo) {
		this.dishNo = dishNo;
	}
    
    
}
