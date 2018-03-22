package com.mongodb.dao;

import java.util.List;
import java.util.Map;

import com.mongodb.collection.UserList;

public interface UserDao {
	//���
		public void insert(UserList object,String collectionName);
		//������������
		public UserList findOne(Map<String,Object> params,String collectionName);
		//��������
		public List<UserList> findAll(String collectionName);
		//�޸�
		public void update(Map<String,Object> params); 
		//��������
		public void createCollection(String collectionName);
		//��������ɾ��
		public void remove(Map<String,Object> params);
}
