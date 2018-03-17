package org.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.util.LayuiPage;
import org.util.PageUtil;
@Repository("baseDao")
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao{
	/**
     * @Resource 自动装配 相当于get() set()
     */
	@Resource(name = "sessionFactory")
	private void setSessionFactoryOverride(SessionFactory sessionFactory){
		// 这个方法名可以随便写，@Resource可以通过name 或者type来装载的。
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 1 getCurrentSession创建的session会和绑定到当前线程,而openSession每次创建新的session。
	 * 2 getCurrentSession创建的线程会在事务回滚或事物提交后自动关闭,而openSession必须手动关闭
	 */
	
	/**
	 * createQuery与createSQLQuery两者区别是：
	 * 前者用的hql语句进行查询，后者可以用sql语句查询 
	 * 
	 * hibernate4中用getCurrentSession()获取session，save()和saveOrUpdate()方法没问题，但是update()和delete()方法没有同步到数据库
	 * 原因：事务没有提交或者session没有及时写入。
	 * 解决方法（3种）： 
	 * 1、update()改为saveOrUpdate()——推荐 
	 * 2、在update()和delete()后加session.flush()———推荐 
	 * 3、事务begin，操作，提交，关闭 
	 * 4、用createQuery(sql).executeUpdate()更新
	 */
	/**
	 * 无需分页
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getObjectByParams(Class<?> className, StringBuffer sql, List<Object> params) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query= session.createSQLQuery(sql.toString()).setResultTransformer(Transformers.aliasToBean(className));
			if(params!=null&&params.toArray().length>0){
				Object[] obj = params.toArray();
				for(int i=0;i<obj.length;i++){
					query.setParameter(i, obj[i]);
				}
			}
			List<Object> list = query!=null?query.list():null;
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 需分页
	 */
	@Override
	public List<Object> getObjectByParams(Class<?> className, StringBuffer sql, List<Object> params,LayuiPage lp) {
		try {
			getCount(sql, params, lp);
			return getList(className, sql, params, lp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取分页list数据
	 * @param className
	 * @param sql
	 * @param params
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getList(Class<?> className, StringBuffer sql, List<Object> params,LayuiPage lp){
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query = null;
			if(lp!=null){
				query= session.createSQLQuery(sql.toString()).setFirstResult(lp.getStart()).setMaxResults(lp.getEnd()).setResultTransformer(Transformers.aliasToBean(className));
			}else{
				query= session.createSQLQuery(sql.toString()).setResultTransformer(Transformers.aliasToBean(className));
			}
			if(params!=null&&params.toArray().length>0){
				Object[] obj = params.toArray();
				for(int i=0;i<obj.length;i++){
					query.setParameter(i, obj[i]);
				}
			}
			List<Object> list = query!=null?query.list():null;
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取检索总数
	 * @param sql
	 * @param params
	 * @param page
	 * @return
	 */
	public Integer getCount( StringBuffer sql, List<Object> params,LayuiPage lp){
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			StringBuffer s = new StringBuffer("select count(1) from (").append(sql).append(") t ");
			Query query= session.createSQLQuery(s.toString());
			if(params!=null&&params.toArray().length>0){
				Object[] obj = params.toArray();
				for(int i=0;i<obj.length;i++){
					query.setParameter(i, obj[i]);
				}
			}
			if(lp!=null){
				lp.setTotalSize(query!=null?Integer.parseInt(query.uniqueResult().toString()):0);
				return lp.getTotalSize();
			}else{
				return query!=null?Integer.parseInt(query.uniqueResult().toString()):0;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	/**
	 * 需分页
	 */
	@Override
	public Map<Object,Object> getObjectByParams(Class<?> className, StringBuffer sql, List<Object> params, PageUtil page) {
		Map<Object,Object> map = new HashMap<Object, Object>();
		try {
			List<Object> list = getList(className,sql,params,page);
			int count = getCount(sql, params, page);
			page.setTotalSize(count);
			
			map.put("list", list);
			map.put("page", page);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取分页list数据
	 * @param className
	 * @param sql
	 * @param params
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getList(Class<?> className, StringBuffer sql, List<Object> params, PageUtil page){
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query= session.createSQLQuery(sql.toString()).setFirstResult(page.getFirstResult()).setMaxResults(page.getPageSize()).setResultTransformer(Transformers.aliasToBean(className));
			if(params!=null&&params.toArray().length>0){
				Object[] obj = params.toArray();
				for(int i=0;i<obj.length;i++){
					query.setParameter(i, obj[i]);
				}
			}
			List<Object> list = query!=null?query.list():null;
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取检索总数
	 * @param sql
	 * @param params
	 * @param page
	 * @return
	 */
	public Integer getCount( StringBuffer sql, List<Object> params, PageUtil page){
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			StringBuffer s = new StringBuffer("select count(1) from (").append(sql).append(") t ");
			Query query= session.createSQLQuery(s.toString());
			if(params!=null&&params.toArray().length>0){
				Object[] obj = params.toArray();
				for(int i=0;i<obj.length;i++){
					query.setParameter(i, obj[i]);
				}
			}
			return (query!=null?Integer.parseInt(query.uniqueResult().toString()):0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 保存/添加
	 */
	@Override
	public boolean save(Object obj){
		try {
			this.getSessionFactory().getCurrentSession().save(obj);
			return true;
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	/**
	 * 更新
	 */
	@Override
	public boolean update(Object obj) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			session.update(obj);
			session.flush();
			return true;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * 删除
	 */
	@Override
	public boolean delete(Object obj) {
		try {
			
			Session session = this.getSessionFactory().getCurrentSession();
			session.delete(obj);
			session.flush();
			return true;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * 通过Id检索对象
	 */
	@Override
	public Object getObjById(Class<?> className,int id) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			return session.get(className, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 批量操作，并返回操作数据数量
	 */
	@Override
	public Integer batchOperationBySql(StringBuffer sql, List<Object> params) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql.toString());
		if(params!=null&&params.toArray().length>0){
			Object[] obj = params.toArray();
			for(int i=0;i<obj.length;i++){
				query.setParameter(i, obj[i]);
			}
		}
		return query.executeUpdate();
	}

	@Override
	public Object getObjectBySql(Class<?> className, StringBuffer sql, List<Object> params) {
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query = null;
			query= session.createSQLQuery(sql.toString()).setResultTransformer(Transformers.aliasToBean(className));
			if(params!=null&&params.toArray().length>0){
				Object[] obj = params.toArray();
				for(int i=0;i<obj.length;i++){
					query.setParameter(i, obj[i]);
				}
			}
			Object obj = query!=null?query.uniqueResult():null;
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
