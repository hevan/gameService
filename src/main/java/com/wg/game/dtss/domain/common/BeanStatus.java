package com.wg.game.dtss.domain.common;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;


@Embeddable
public class BeanStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private Integer status;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date createdDate;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	@Transient
	public String getCreateDateString() {
		if(null != this.getCreatedDate()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.format(this.getCreatedDate());
		}
		return "";
	}
	
	
}
