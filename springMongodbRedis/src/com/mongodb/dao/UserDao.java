package com.mongodb.dao;

import java.util.List;
import java.util.Map;

import com.mongodb.collection.UserList;

public interface UserDao {
	//添加
		public void insert(UserList object,String collectionName);
		//根据条件查找
		public UserList findOne(Map<String,Object> params,String collectionName);
		//查找所有
		public List<UserList> findAll(String collectionName);
		//修改
		public void update(Map<String,Object> params); 
		//创建集合
		public void createCollection(String collectionName);
		//根据条件删除
		public void remove(Map<String,Object> params);
}
