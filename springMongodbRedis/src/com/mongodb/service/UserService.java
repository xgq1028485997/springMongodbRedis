package com.mongodb.service;

import java.util.List;
import java.util.Map;

import com.mongodb.collection.UserList;

public interface UserService {
	
	List<UserList> getUserList(Map<String, Object> params, String collectionName);

	void addUserList(UserList userList, String collectionName);

	void updateUserList(Map<String, Object> params, String collectionName);

}
