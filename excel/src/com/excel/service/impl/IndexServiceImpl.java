package com.excel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.dao.BaseDao;
import org.springframework.stereotype.Service;

import com.excel.hibernate.common.FunctionList;
import com.excel.service.IndexService;
import com.excel.sql.BaseInfoSql;

@Service("indexService")
public class IndexServiceImpl implements IndexService{
	@Resource(name="baseDao")
	private BaseDao baseDao;

	@Override
	public List<Object> getFunctionList(StringBuffer where, List<Object> params) {
		StringBuffer sql = new StringBuffer(BaseInfoSql.getExcelFucntion);
		sql.append( where.toString());
		sql.append(" order by fl.function_sort ");
		return baseDao.getObjectByParams(FunctionList.class, sql, params);
	}
}
