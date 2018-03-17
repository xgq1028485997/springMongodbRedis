package com.excel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.dao.BaseDao;
import org.springframework.stereotype.Service;
import org.util.LayuiPage;

import com.excel.hibernate.common.FunctionList;
import com.excel.service.FunctionService;
import com.excel.sql.FunctionListSql;

@Service("functionService")
public class FunctionServiceImpl implements FunctionService{
	@Resource(name="baseDao")
	private BaseDao baseDao;

	@Override
	public List<Object> getFunctionList(StringBuffer sb, List<Object> obj, LayuiPage lp) {
		StringBuffer sql = new StringBuffer(FunctionListSql.getFunctionList());
		sql.append(sb);
		return baseDao.getObjectByParams(FunctionList.class, sql, obj,lp);
	}
}
