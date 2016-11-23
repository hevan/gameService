package com.wg.game.dtss.domain.common;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "game_type")
public class GameType implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//主键
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//游戏名称
	@Column(length = 20)
	private String name;
	
	//对战类型（1：好友对战、2：世界对战）
	@Column(length = 10)
	private String type;
	
	//消耗金币数
	private BigDecimal usedGold;
	
	//游戏类型图标
	@Column(length = 100)
	private String imageUrl;
	
	//游戏信息说明-用户
	@Column(length = 2000)
	private String information;
	
	//麻将规则-技术人员
	@Column(length = 2000)
	private String rules;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}


	public BigDecimal getUsedGold() {
		return usedGold;
	}

	public void setUsedGold(BigDecimal usedGold) {
		this.usedGold = usedGold;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
