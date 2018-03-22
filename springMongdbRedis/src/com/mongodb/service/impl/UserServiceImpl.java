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
	
	@Cacheable(value = "getUserList",keyGenerator = "keyGenerator")
	@Override
	public List<Object> getUserList(Map<String, Object> params) {
		System.out.println("无Redis缓存，查询数据库 ");
		return userDao.find(params,UserList.class);
	}
	//不管之前是否有缓存，都按逻辑执行，并缓存
	@CachePut(value={"getUserList"},key="#user.getUserName()")
	@Override
	public void addUserList(UserList userList, String collectionName) {
		userDao.insert(userList, collectionName);
	}
	//删除多个或所部缓存
	@CacheEvict(value={"getUserList"},allEntries=true)
	@Override
	public void updateUserList(Map<String, Object> params, String collectionName) {
		userDao.update(params,UserList.class);
	}

}
