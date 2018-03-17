package com.excel.service;

import java.util.List;

import org.util.LayuiPage;

import com.excel.hibernate.common.SerialNumberList;
import com.excel.hibernate.common.UserList;

public interface UserService {
	/**
	 * 添加/修改人员
	 * @param ul
	 */
	void addOrEditUser(UserList ul);
	/**
	 * 检索人员信息无需分页
	 * @param sb
	 * @param obj
	 * @return
	 */
	List<Object> getUserList(StringBuffer sb, List<Object> obj,LayuiPage lp);
	/**
	 * 删除n个人员信息
	 * @param ul
	 */
	void delUser(List<UserList> ul);
	/**
	 * 通过对象ID获取整个对象
	 * @param className
	 * @param id
	 * @return
	 */
	Object getObjById(Class<?> className,int id);
	
	SerialNumberList addSerialNumber();

}
