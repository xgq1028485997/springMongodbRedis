package com.excel.service;

import java.util.List;

import org.util.LayuiPage;

import com.excel.hibernate.common.TypeList;

public interface TypeService {

	List<Object> getTypeList(StringBuffer sb, List<Object> obj, LayuiPage lp);

	Object getObjById(Class<?> cls, Integer id);

	void addOrEditType(TypeList tl);

	void delType(List<TypeList> tl);

}
