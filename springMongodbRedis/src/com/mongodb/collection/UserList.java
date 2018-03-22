package com.mongodb.collection;

import java.io.Serializable;

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
	@Field(value="addtime")
	private String addtime;
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
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	@Override
    public String toString() {
        return "ItemInfo [userId=" + userId + ", userName="
                + userName + ", userNo=" + userNo + ", userPassword=" + userPassword 
                + "addtime="+addtime+"]";
    }
}
