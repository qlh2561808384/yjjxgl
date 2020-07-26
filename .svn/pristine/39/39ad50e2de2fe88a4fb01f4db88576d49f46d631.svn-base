package com.datanew.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public interface BaseDao {
	Session getSession();
	/**
	 * @param object
	 * @return Object
	 * @author hjz
	 * @date 2015年10月31日 下午4:46:53
	 */

	 Object save(Object object);



	/**
	 * @param  object
	 * @return Object
	 * @Description: 更新对象
	 * @author hjz
	 * @date 2015年10月31日 下午4:47:34
	 */
	 Object update(Object object);



	/**
	 * @param  object
	 * @return Object
	 * @throws
	 * @Description:(新增或更新)对象
	 * @author hjz
	 * @date 2015年10月31日 下午4:47:41
	 */
	 Object saveOrUpdate(Object object);



	/**
	* @Description: 删除对象
	* @param  object
	* @return void
	* @throws
	* @author hjz
	* @date 2015年10月31日 下午4:47:48
	 */
	public void delete(Object object);

	/**
	 *
	* @Description: 通过主键得到对象
	* @param  clazz
	* @param  id
	* @return Object
	* @throws
	* @author hjz
	* @date 2015年10月31日 下午4:49:46
	 */
	public Object load(Class clazz, Serializable id);

	/**
	 *
	* @Description: 加载所有对象
	* @param  clazz
	* @return List
	* @throws
	* @author hjz
	* @date 2015年10月31日 下午4:50:55
	 */
	public List loadAll(Class clazz);

	/**
	 * @param clazz
	 * @param propertyName
	 * @param value
	 * @return List
	 * @Description: 根据属性查询结果
	 * @author hjz
	 * @date 2015年10月31日 下午5:21:40
	 */
	 List selectByProperty(String clazz, String propertyName, String value) ;


	/**
	 * @param hql
	 * @return List
	 * @throws
	 * @Description: 根据hql语句和变量进行查询
	 * @author hjz
	 * @date 2015年10月31日 下午4:51:12
	 */
	 List selectByHql(String hql);




	/**
	 * 根据hql语句和变量进行查询
	 * @param hql
	 * @param values
	 * @return
	 * @author hjz
	 * @date 2015年10月31日 下午4:51:26
	 */
	 List selectByHql(String hql, List values);




	/**
	 * @Description: 根据hql语句和变量进行分页查询
	 * @param hql
	 * @param start
	 * @param limit
	 * @return
	 * @author hjz
	 * @date 2015年10月31日 下午4:59:27
	 */
	public List selectByHql(String hql, int start, int limit);

	/**
	 * @Description: 根据hql语句和变量进行分页查询
	 * @param hql
	 * @param values
	 * @param start
	 * @param limit
	 * @return
	 * @author hjz
	 * @date 2015年10月31日 下午5:00:34
	 */
	public List selectByHql(String hql, List values, int start, int limit);

	/**
	 * @Description: 查询记录数
	 * @param HQL
	 * @param values
	 * @return
	 * @author hjz
	 * @date 2015年10月31日 下午4:57:48
	 */
	/*public int getCountByHQL(final String HQL,Object... values);*/

	/**
	 * @Description: 根据sql语句和变量进行查询
	 * @param sql
	 * @return  List
	 * @author hjz
	 * @date 2015年10月31日 下午4:51:36
	 */
	public List selectBySql(String sql);

	/**
	 * @Description: 根据sql语句和变量进行查询
	 * @param sql
	 * @param obj
	 * @return List
	 * @author hjz
	 * @date 2015年10月31日 下午4:57:48
	 */
	public List selectBySql(String sql, Object[] obj);

	/**
	 * @Description: 根据sql语句和变量进行查询
	 * @param sql
	 * @param obj
	 * @param start
	 * @param limit
	 * @return
	 * @author hjz
	 * @date 2015年10月31日 下午4:57:48
	 */
	public List selectBySql(String sql, Object[] obj,int start ,int limit);

	/**
	 * @Description: 根据sql语句返回map类型的list
	 * @param sql
	 * @return
	 * @author hjz
	 * @date 2015年10月31日 下午11:00:26
	 */
	public List selectMapsBySQL(String sql);

	/**
	 * @param sql
	 * @param params
	 * @return List
	 * @Description: 根据sql语句返回map类型的list
	 * @author hjz
	 * @date 2015年10月31日 下午11:00:47
	 */
	 List selectMapsBySQL(String sql, List params);



	/**
	 * @param sql
	 * @param params
	 * @param start
	 * @param limit
	 * @return List
	 * @Description: 根据sql语句返回map类型的list
	 * @author hjz
	 * @date 2015年10月31日 下午11:01:02
	 */
	 List selectMapsBySQL(String sql, List params, int start, int limit);




	/**
	 * @param sql
	 * @return void
	 * @Description: 根据sql更新或删除数据
	 * @author hjz
	 * @date 2015年10月31日 下午4:52:40
	 */
	 void executeBySql(String sql);
	void executeProc(String proName,List<String> params);




	/**
	 * @Description: 刷新HIBERNATE缓存
	 * @author hjz
	 * @date 2015年10月31日 下午4:54:49
	 */
	public void flush();

	/**
	 * @param sql
	 * @return String
	 * @Description: 根据sql语句获取Clob字段
	 */
	 String getClobBySql(String sql);



	/**
	 * @param sql
	 * @return
	 * @Description: 根据sql语句获取Blob字段
	 */
	 String getBlobStrBySql(String sql);



	/**
	 * @Description: 保存blob字符串
	 * @param sql
	 * @param content
	 */
	 void updateBlobBySql(String sql, String content);



	/**
	 *@Description:  保存大容量字符串
	 * @param sql
	 * @param content
	 */
	public void updateLongraw(String sql, String content);

	/**
	 * @Description: 获取大容量字符字段
	 * @param sql
	 * @return String
	 */
	public String getLongrawBySql(String sql);


	/**
	 * @Description: 事务回滚 rollback:(这里用一句话描述这个方法的作用).
	 * @param @return
	 * @return boolean
	 * @Description:
	 * @author hjz
	 * @date 2015年10月31日 下午5:02:15
	 */
	 boolean rollback();
    /**
     * 
    * @Description: 根据hql语句返回对象
    * @param @param hql
    * @param @param values    
    * @return void   
    * @throws 
    * @author hjz 
    * @date 2015年12月8日 上午9:26:21
     */
	Object loadByHql(String hql, List values);
	public Connection getConnection();
	public  <T> List<T> getListfromSql(String sql,Class<T>clazz);
	public  <T> List<T> getListfromSql(Connection conn,String sql,Class<T>clazz);
	Session getNewSession();
	void clearObject(Object obj);
	/**
     * 
    * @Description: 根据sql语句返回条数
    * @param @param hql
    * @param @param values    
    * @return void   
    * @throws 
    * @author hjz 
    * @date 2015年12月8日 上午9:26:21
     */
	public abstract Long getCountBySQL(String paramString, List paramList);
	public abstract Long getCountByHQL(String paramString, List paramList);

}



