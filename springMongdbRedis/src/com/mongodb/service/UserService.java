package com.mongodb.service;

import java.util.List;
import java.util.Map;

import com.mongodb.collection.UserList;

public interface UserService {
	
	List<Object> getUserList(Map<String, Object> params);

	void addUserList(UserList userList, String collectionName);

	void updateUserList(Map<String, Object> params, String collectionName);

}
