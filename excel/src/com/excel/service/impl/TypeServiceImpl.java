package com.excel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.dao.BaseDao;
import org.springframework.stereotype.Service;
import org.util.LayuiPage;

import com.excel.hibernate.common.TypeList;
import com.excel.service.TypeService;
import com.excel.sql.TypeListSql;
@Service("typeService")
public class TypeServiceImpl implements TypeService{
	@Resource(name="baseDao")
	private BaseDao baseDao;

	@Override
	public List<Object> getTypeList(StringBuffer sb, List<Object> obj, LayuiPage lp) {
		StringBuffer sql = new StringBuffer(TypeListSql.getTypeList());
		sql.append(sb);
		return baseDao.getObjectByParams(TypeList.class, sql, obj,lp);
	}

	@Override
	public Object getObjById(Class<?> cls, Integer id) {
		return baseDao.getObjById(cls, id);
	}

	@Override
	public void addOrEditType(TypeList tl) {
		if(tl.getTypeId()!=null){
			baseDao.update(tl);
		}else{
			baseDao.save(tl);
		}
	}

	@Override
	public void delType(List<TypeList> tl) {
		for(TypeList t : tl){
			baseDao.delete(t);
		}
	}
	
	
	
}
