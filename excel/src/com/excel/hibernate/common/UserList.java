package com.excel.hibernate.common;

import java.io.Serializable;
import java.util.Date;

public class UserList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userNo;
	private String userName;
	private String userPassword;
	private Date userAddtime;
	private String userSex;
	private Date userBirth;
	private String userProvince;
	private String userCity;
	private String userCounty;
	private String userAddr;
	private String userTel;
	private String userCard;
	private String userRemark;
	private String userVisible;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Date getUserAddtime() {
		return userAddtime;
	}
	public void setUserAddtime(Date userAddtime) {
		this.userAddtime = new java.util.Date(userAddtime.getTime());
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserProvince() {
		return userProvince;
	}
	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserCounty() {
		return userCounty;
	}
	public void setUserCounty(String userCounty) {
		this.userCounty = userCounty;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserCard() {
		return userCard;
	}
	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}
	public String getUserVisible() {
		return userVisible;
	}
	public void setUserVisible(String userVisible) {
		this.userVisible = userVisible;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public Date getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(Date userBirth) {
		this.userBirth = new java.util.Date(userBirth.getTime());
	}
	
	
}
