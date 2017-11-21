package com.lxg.springboot.model;

import java.io.Serializable;
import java.util.List;

public class OrderAll extends BasicObject {

	private static final long serialVersionUID = 1L;
	private String mch_id;// 商户号
	private String orderNo;// 商户订单号
	private String orderNo1;// 商户订单号
	private String description;// 商品描述
	private double fee;// 标价金额 ,单位为分
	private String openid;// 用户标识
	private int usedScore;// 抵扣积分
	private int bonusScore;// 奖励积分
	private double charges;//手续费
	private int state;//状态
	private int checkstate;//状态
	private String time;//时间
	private List<OrderGood> temp;
	private String startDate;//时间
	private String endDate;//时间
	private int page;
	private Shop store;
	private String code;
	private String storeid1;
	private int amount;
	private String name;
	private String specifi;
	private double price;

	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}


	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public int getUsedScore() {
		return usedScore;
	}

	public void setUsedScore(int usedScore) {
		this.usedScore = usedScore;
	}

	public int getBonusScore() {
		return bonusScore;
	}

	public void setBonusScore(int bonusScore) {
		this.bonusScore = bonusScore;
	}

	public double getCharges() {
		return charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<OrderGood> getTemp() {
		return temp;
	}

	public void setTemp(List<OrderGood> temp) {
		this.temp = temp;
	}

	public int getCheckstate() {
		return checkstate;
	}

	public void setCheckstate(int checkstate) {
		this.checkstate = checkstate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Shop getStore() {
		return store;
	}

	public void setStore(Shop store) {
		this.store = store;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecifi() {
		return specifi;
	}

	public void setSpecifi(String specifi) {
		this.specifi = specifi;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getOrderNo1() {
		return orderNo1;
	}

	public void setOrderNo1(String orderNo1) {
		this.orderNo1 = orderNo1;
	}

	public String getStoreid1() {
		return storeid1;
	}

	public void setStoreid1(String storeid1) {
		this.storeid1 = storeid1;
	}


}
