package org.dao;

import java.util.List;
import java.util.Map;

import org.util.LayuiPage;
import org.util.PageUtil;

public interface BaseDao {

	//无需分页
	List<Object> getObjectByParams(Class<?> className, StringBuffer sql, List<Object> params);
	//需分页
	List<Object> getObjectByParams(Class<?> className, StringBuffer sql, List<Object> params,LayuiPage lp);
	//需分页
	Map<Object,Object> getObjectByParams(Class<?> className, StringBuffer sql, List<Object> params, PageUtil page);
	//保存/添加
	boolean save(Object obj);
	//删除
	boolean delete(Object obj);
	//更新
	boolean update(Object obj);
	
	/**
	 * 通过对象Id获取整个对象
	 * @param className
	 * @param id
	 * @return
	 */
	Object getObjById(Class<?> className,int id);
	/**
	* @Title: batchOperationBySql 
	* @Description: 批量操作数据 增 删 改
	* @param sql
	* @param params
	* @return
	* @return: Integer 返回操作数据数量
	 */
	Integer batchOperationBySql(StringBuffer sql,List<Object> params);
	
	Object getObjectBySql(Class<?> className, StringBuffer sql, List<Object> params);

}
