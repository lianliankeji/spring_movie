package com.lxg.springboot.model;

import java.io.Serializable;

public class User extends BasicObject {

	/**
	 * author zhenghong@xrfinance.com
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String openid;

	private String vip;

	private int frt;

	private String viptime;

	private String last1id;
	
	private int last1type;
	
	private int last1ep;
	
	private String last2id;
	
	private int last2type;
	
	private int last2ep;
	
	private String last3id;
	
	private int last3type;
	
	private int last3ep;
	
	private int time;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public int getFrt() {
		return frt;
	}

	public void setFrt(int frt) {
		this.frt = frt;
	}

	public String getViptime() {
		return viptime;
	}

	public void setViptime(String viptime) {
		this.viptime = viptime;
	}

	public String getLast1id() {
		return last1id;
	}

	public void setLast1id(String last1id) {
		this.last1id = last1id;
	}

	public int getLast1type() {
		return last1type;
	}

	public void setLast1type(int last1type) {
		this.last1type = last1type;
	}

	public int getLast1ep() {
		return last1ep;
	}

	public void setLast1ep(int last1ep) {
		this.last1ep = last1ep;
	}

	public String getLast2id() {
		return last2id;
	}

	public void setLast2id(String last2id) {
		this.last2id = last2id;
	}

	public int getLast2type() {
		return last2type;
	}

	public void setLast2type(int last2type) {
		this.last2type = last2type;
	}

	public int getLast2ep() {
		return last2ep;
	}

	public void setLast2ep(int last2ep) {
		this.last2ep = last2ep;
	}

	public String getLast3id() {
		return last3id;
	}

	public void setLast3id(String last3id) {
		this.last3id = last3id;
	}

	public int getLast3type() {
		return last3type;
	}

	public void setLast3type(int last3type) {
		this.last3type = last3type;
	}

	public int getLast3ep() {
		return last3ep;
	}

	public void setLast3ep(int last3ep) {
		this.last3ep = last3ep;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}