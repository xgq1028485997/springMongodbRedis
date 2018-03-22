package com.mongodb.collection;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="user_list")
public class UserList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field(value="user_id")
	private String userId;
	@Field(value="user_name")
	private String userName;
	@Field(value="user_password")
	private String userPassword;
	@Field(value="user_no")
	private String userNo;
	@Field(value="user_addtime")
	private String userAddtime;
	@Field(value="user_brith")
	private Date userBrith;
	@Field(value="user_province")
	private String userProvince;
	@Field(value="user_city")
	private String userCity;
	@Field(value="user_county")
	private String userCounty;
	@Field(value="user_addr")
	private String userAddr;
	@Field(value="user_tel")
	private String userTel;
	@Field(value="user_card")
	private String userCard;
	@Field(value="user_remark")
	private String userRemark;
	@Field(value="user_visible")
	private String userVisible;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
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
	
	public String getUserAddtime() {
		return userAddtime;
	}
	public void setUserAddtime(String userAddtime) {
		this.userAddtime = userAddtime;
	}
	public Date getUserBrith() {
		return userBrith;
	}
	public void setUserBrith(Date userBrith) {
		this.userBrith = userBrith;
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
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public String getUserVisible() {
		return userVisible;
	}
	public void setUserVisible(String userVisible) {
		this.userVisible = userVisible;
	}
	
}
