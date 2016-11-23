package com.wg.game.dtss.domain.common;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

public class GameAds implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7807595030673894931L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Column(length = 50)
	private String name;
	
	@Column(length = 255)
	private String img;
	
	@Column(length = 255)
	private String url;
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinTable(name = "game_ads_relation", joinColumns = {@javax.persistence.JoinColumn(name = "game_ads_id") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "game_type_id") })
	private Set<GameType> gameTypes;
	
	private BeanStatus beanStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<GameType> getGameTypes() {
		return gameTypes;
	}

	public void setGameTypes(Set<GameType> gameTypes) {
		this.gameTypes = gameTypes;
	}

	public BeanStatus getBeanStatus() {
		return beanStatus;
	}

	public void setBeanStatus(BeanStatus beanStatus) {
		this.beanStatus = beanStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
