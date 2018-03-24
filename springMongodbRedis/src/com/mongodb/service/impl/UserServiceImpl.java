package com.mongodb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mongodb.collection.UserList;
import com.mongodb.dao.UserDao;
import com.mongodb.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Cacheable("getUserList")
	@Override
	public List<UserList> getUserList(Map<String, Object> params, String collectionName) {
		log.info("无Redis时，查询数据库……");
		return userDao.findAll(collectionName);
	}
	@CachePut(value="getUserList")
	@Override
	public void addUserList(UserList userList, String collectionName) {
		userDao.insert(userList, collectionName);
	}
	
	@CacheEvict(value={"getUserList"},allEntries=true)
	@Override
	public void updateUserList(Map<String, Object> params,Map<String,Object> where) {
		userDao.update(params,where);
	}

}
