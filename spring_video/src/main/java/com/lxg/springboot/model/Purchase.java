package com.lxg.springboot.model;

import java.io.Serializable;

public class Purchase implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	
	private String openid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}