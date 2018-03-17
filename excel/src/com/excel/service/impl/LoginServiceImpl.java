package com.excel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.dao.BaseDao;
import org.springframework.stereotype.Service;

import com.excel.hibernate.common.UserList;
import com.excel.service.LoginService;
import com.excel.sql.UserListSql;
@Service("loginService")
public class LoginServiceImpl implements LoginService{
	@Resource(name="baseDao")
	private BaseDao baseDao;

	@Override
	public List<Object> getUserList(StringBuffer where,List<Object> params) {
		StringBuffer sql = new StringBuffer(UserListSql.getUserList());
		sql.append(where.toString());
		return baseDao.getObjectByParams(UserList.class,sql,params);
	}

}
