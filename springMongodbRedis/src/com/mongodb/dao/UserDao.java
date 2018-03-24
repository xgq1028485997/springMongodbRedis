package com.mongodb.dao;

import java.util.List;
import java.util.Map;

import com.mongodb.collection.UserList;

public interface UserDao {
	public void insert(Object object,String collectionName);
	public UserList findOne(Map<String,Object> params,String collectionName);
	public List<UserList> findAll(String collectionName);
	public void update(Map<String,Object> params,Map<String,Object> where); 
	public void createCollection(String collectionName);
	public void remove(Map<String,Object> params);
	
	public List<Object> find(Map<String,Object> params,Class<?> cls);
}
