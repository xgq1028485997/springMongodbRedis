package com.mongodb.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
	public Object findOne(Map<String,Object> params,Class<?> cls) {
		return mongoTemplate.findOne(new Query(Criteria.where("userId").is(params.get("userId"))),cls);
	}
	
	@Override
	public List<UserList> findAll(String collectionName) {
		List<UserList> result = mongoTemplate.findAll(UserList.class);
		return result;
	}
	
	@Override
	public void update(Map<String,Object> params,Class<?> cls) {
		//mongoTemplate.update(new Query(Criteria.where("userId").is(params.get("userId"))), new Update().set("userName", params.get("userName")));
	}
	
	@Override
	public void createCollection(String name) {
		this.createCollection(name);
	}
	
	@Override
	public void remove(Map<String, Object> params,Class<?> cls) {
		//this.remove(new Query(Criteria.where("userId").is(params.get("userId"))));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> find(Map<String, Object> params,Class<?> cls) {
		Query query = new Query();
		if(params != null){
			for(Entry<String, Object> entry : params.entrySet()){
				query.addCriteria(Criteria.where(entry.getKey()).is(entry.getValue()));
			}
		}
		return (List<Object>) mongoTemplate.find(query, cls);
	}

}
