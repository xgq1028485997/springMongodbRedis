package com.mongodb.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mongodb.collection.UserList;
import com.mongodb.dao.UserDao;
import com.mongodb.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Cacheable(value="getUserList",keyGenerator = "keyGenerator")
	@Override
	public List<UserList> getUserList(Map<String, Object> params, String collectionName) {
		return userDao.findAll(collectionName);
	}
	@CachePut(value="addUserList")
	@Override
	public void addUserList(UserList userList, String collectionName) {
		userDao.insert(userList, collectionName);
	}
	
	@CacheEvict(value={"getUserList,addUserList"},allEntries=true)
	@Override
	public void updateUserList(Map<String, Object> params, String collectionName) {
		userDao.update(params);
	}

}
