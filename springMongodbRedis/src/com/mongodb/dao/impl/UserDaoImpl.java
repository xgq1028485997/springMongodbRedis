package com.mongodb.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.base.MongoBase;
import com.mongodb.collection.UserList;
import com.mongodb.dao.UserDao;
@Repository("userDao")
public class UserDaoImpl implements UserDao{
	
	@Resource
	private MongoTemplate mongoTemplate;
	
	@Override
	public void insert(UserList object,String collectionName) {
		//this.save(object);
	}
	
	@Override
	public UserList findOne(Map<String,Object> params,String collectionName) {
		//return this.findOne(new Query(Criteria.where("userId").is(params.get("userId"))));
		return null;
	}
	
	@Override
	public List<UserList> findAll(String collectionName) {
		List<UserList> result = mongoTemplate.findAll(UserList.class);
		return result;
	}
	
	@Override
	public void update(Map<String,Object> params) {
		//mongoTemplate.update(new Query(Criteria.where("userId").is(params.get("userId"))), new Update().set("userName", params.get("userName")));
	}
	
	@Override
	public void createCollection(String name) {
		this.createCollection(name);
	}
	
	@Override
	public void remove(Map<String, Object> params) {
		//this.remove(new Query(Criteria.where("userId").is(params.get("userId"))));
	}

}
