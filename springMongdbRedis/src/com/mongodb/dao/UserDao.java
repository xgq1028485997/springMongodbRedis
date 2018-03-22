package com.mongodb.dao;

import java.util.List;
import java.util.Map;

import com.mongodb.collection.UserList;

public interface UserDao {
	//���
		public void insert(UserList object,String collectionName);
		//�����������
		public Object findOne(Map<String,Object> params,Class<?> cls);
		//��������
		public List<UserList> findAll(String collectionName);
		//�޸�
		public void update(Map<String,Object> params,Class<?> cls); 
		//��������
		public void createCollection(String collectionName);
		//�������ɾ��
		public void remove(Map<String,Object> params,Class<?> cls);
		
		public List<Object> find(Map<String, Object> params,Class<?> cls);
}
