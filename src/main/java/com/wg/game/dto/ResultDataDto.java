package com.wg.game.dto;

import java.io.Serializable;

public class ResultDataDto<T> implements Serializable{

	public ResultDataDto() {
	
		// TODO Auto-generated constructor stub
	}

	//错误对象
	public ResultDataDto(String errorCode, String message) {
		this.ret = errorCode;
		this.message = message;
	}
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8990763402745743799L;

	private String ret = "0";
	private String message ="success";
	
	private T data;

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
