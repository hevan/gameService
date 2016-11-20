package com.wg.game.domain.common;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User implements Serializable {
	
	public User()
	{
		
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username", length = 50)
	private String username;

	@Column(name = "password", length = 500)
	private String password;

	@Column(name = "nickName", length = 255)
	private String nickName;
	
	@Column(length = 64)
	private String openid;

	@Column(length = 255)
	private String token;

	@Column(name = "mobile", length = 255)
	private String mobile;

	@Column(length = 255)
	private String headImageUrl;
	
	@Column(length = 20)
	private String city;
	
	@Column(length = 2)
	private Integer sex;
	
	private BigDecimal masonry;
	
	private BigDecimal gold;
	
	@Column(length = 20)
	private String recommend;
	

	@Embedded
	private BeanStatus beanStatus;//状态信息

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHeadImageUrl() {
		return headImageUrl;
	}

	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public BigDecimal getMasonry() {
		return masonry;
	}

	public void setMasonry(BigDecimal masonry) {
		this.masonry = masonry;
	}

	public BigDecimal getGold() {
		return gold;
	}

	public void setGold(BigDecimal gold) {
		this.gold = gold;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public BeanStatus getBeanStatus() {
		return beanStatus;
	}

	public void setBeanStatus(BeanStatus beanStatus) {
		this.beanStatus = beanStatus;
	}

}