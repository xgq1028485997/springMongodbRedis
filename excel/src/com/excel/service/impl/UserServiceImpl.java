package com.excel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.BaseDao;
import org.springframework.stereotype.Service;
import org.util.LayuiPage;
import org.util.Utils;

import com.excel.hibernate.common.SerialNumberList;
import com.excel.hibernate.common.UserList;
import com.excel.service.UserService;
import com.excel.sql.UserListSql;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource(name="baseDao")
	private BaseDao baseDao;

	@Override
	public void addOrEditUser(UserList user) {
		if(user.getUserId()!=null){
			baseDao.update(user);
		}else{
			baseDao.save(user);
		}
	}
	
	@Override
	public List<Object> getUserList(StringBuffer sb, List<Object> obj,LayuiPage lp) {
		StringBuffer sql = new StringBuffer(UserListSql.getUserList());
		sql.append(sb);
		return baseDao.getObjectByParams(UserList.class, sql, obj,lp);
	}

	@Override
	public void delUser(List<UserList> ul) {
		for(UserList u : ul){
			baseDao.delete(u);
		}
	}

	@Override
	public Object getObjById(Class<?> cls,int id) {
		return baseDao.getObjById(cls,id);
	}

	@Override
	public SerialNumberList addSerialNumber() {
		StringBuffer sql = new StringBuffer(" select snl.serial_number_id serialNumberId,snl.serial_number_no serialNumberNo, ");
		sql.append(" snl.serial_number_prefix serialNumberPrefix,snl.serial_number_num serialNumberNum,snl.serial_number_suffix serialNumberSuffix, ");
		sql.append(" snl.serial_number_connect serialNumberConnect,snl.serial_number_remark serialNumberRemark,");
		sql.append(" snl.serial_number_addtime serialNumberAddtime from serial_number_list snl ");
		sql.append(" where snl.serial_number_type='UN' and snl.serial_number_num =(select MAX(s.serial_number_num) from serial_number_list s ");
		sql.append(" where s.serial_number_type='UN') ");
		List<Object> params = new ArrayList<Object>();
		SerialNumberList snl = (SerialNumberList) baseDao.getObjectBySql(SerialNumberList.class, sql, params);
		SerialNumberList s = new SerialNumberList();
		if(snl == null){
			s.setSerialNumberNo("UN01");
			s.setSerialNumberNum("01");
			s.setSerialNumberPrefix("UN");
			s.setSerialNumberType("UN");
			s.setSerialNumberSuffix("");
			s.setSerialNumberConnect("");
			s.setSerialNumberAddtime(new Date());
			s.setSerialNumberRemark("第1个人员");
		}else{
			Integer snn = Integer.parseInt(snl.getSerialNumberNum()) + 1;
			s.setSerialNumberNum(Utils.getAutoFill(snn.toString(), 1, 0, "0"));
			s.setSerialNumberPrefix("UN");
			s.setSerialNumberType("UN");
			s.setSerialNumberSuffix("");
			s.setSerialNumberConnect("");
			s.setSerialNumberNo(s.getSerialNumberPrefix()+s.getSerialNumberConnect()+s.getSerialNumberNum()+s.getSerialNumberSuffix());
			s.setSerialNumberAddtime(new Date());
			s.setSerialNumberRemark("第"+snn+"个人员");
		}
		
		baseDao.save(s);
		
		return s;
	}
	
}
