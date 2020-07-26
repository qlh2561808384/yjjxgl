package com.datanew.dao.Impl;

import com.datanew.dao.BaseDao;

import org.hibernate.*;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("baseDao")//标注数据访问组件 即是DAO组件
public class BaseDaoImpl implements BaseDao {
    //@Resource按照BYNAME自动注入    @Autowired是按照BYTYPE自动注入的

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    public Session getNewSession(){
        return sessionFactory.openSession();
    }
    //从session缓存(EntityEntries属性)中逐出该对象
    public void clearObject(Object obj) {
        getSession().evict(obj);
    }

    public Object save(Object object) {
        getSession().save(object);
        return object;
    }


    public Connection getConnection() {
        try {
            Connection c = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
            return c;
        } catch (DataAccessResourceFailureException e) {
            e.printStackTrace();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> getListfromSql(String sql, Class<T> clazz) {
        return getListfromSql(null, sql, clazz);
    }

    public Object update(Object object) {
        getSession().update(object);
        return object;
    }

    public Object saveOrUpdate(Object object) {
        getSession().saveOrUpdate(object);
        getSession().saveOrUpdate(object);
        return object;
    }

    public void delete(Object object) {
        getSession().delete(object);
    }

    public Object load(Class clazz, Serializable id) {
        Object o = null;
        try {
            o = getSession().load(clazz, id);
        } catch (Exception e) {
            return null;
        }
        return o;
    }

    public Object loadByHql(String hql, List values) {
        List list = selectByHql(hql, values, 0, 1);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public Object loadByHql(String hql) {
        return loadByHql(hql, null);
    }

    public List loadAll(Class clazz) {
        String hql = "from " + clazz.getName();
        return selectByHql(hql);
    }

    public List selectByProperty(String clazz, String propertyName, String value) {
        String hql = "from " + clazz + " as model where model." + propertyName
                + "= ?";
        return selectByHql(hql);
    }

    public List selectByHql(String hql) {
        return selectByHql(hql, null);
    }


    public List selectByHql(String hql, List values) {
        return selectByHql(hql, values, -1, -1);
    }


    public List selectByHql(String hql, int start, int end) {
        return selectByHql(hql, null, start, end);
    }


    public List selectByHql(String hql, List values, int start, int limit) {
        final int pStart = start;
        final List pvalues = values;
        final int pLimit = limit;
        final String hql1 = hql;

        Query query = getSession().createQuery(hql1);
        if (values != null) {
            for (int i = 0; i < pvalues.size(); i++) {
                if (pvalues.get(i) instanceof String) {
                    query.setString(i, (String) pvalues.get(i));
                } else if (pvalues.get(i) instanceof Date) {
                    query.setDate(i, (Date) pvalues.get(i));
                } else if (pvalues.get(i) instanceof Long) {
                    query.setLong(i, (Long) pvalues.get(i));
                } else {
                    query.setString(i, (String) pvalues.get(i));
                }

            }
        }
        if (start != -1 && limit != -1) {
            query.setMaxResults(pLimit);
            query.setFirstResult(pStart);
        }
        List result = query.list();
        if (!Hibernate.isInitialized(result))
            Hibernate.initialize(result);
        return result;

    }

    /*public int getCountByHQL(String hql, Object... values) {
        Query query = this.getSession().createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        query.setMaxResults(1);
        return (Integer) query.uniqueResult();
    }*/


    public List selectBySql(String sql) {

        return selectBySql(sql, null);

    }

    public List selectBySql(String sql, Object[] obj) {
        return selectBySql(sql, obj, -1, -1);
    }

    public List selectBySql(String sql, Object[] obj, int start, int limit) {
        final int pStart = start;
        final int pLimit = limit;
        final String msql = sql;
        Query query = getSession().createSQLQuery(msql);
        if (obj != null) {
            for (int i = 0; i < obj.length; i++) {
                query.setParameter(i, obj[i]);
            }
        }
        if (start != -1 && limit != -1) {
            query.setMaxResults(pLimit);
            query.setFirstResult(pStart);
        }
        List result = query.list();
        if (!Hibernate.isInitialized(result))
            Hibernate.initialize(result);
        return result;

    }


    public List selectMapsBySQL(String sql) {
        return this.selectMapsBySQL(sql, null, -1, -1);
    }


    public List selectMapsBySQL(String sql, List params) {
        return this.selectMapsBySQL(sql, params, -1, -1);
    }

    public List selectMapsBySQL(String sql, List params, int start, int limit) {
        SQLQuery query = getSession().createSQLQuery(sql);
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                query.setParameter(i, params.get(i));
            }
        }
        if (start != -1 && limit != -1) {
            query.setMaxResults(limit);
            query.setFirstResult(start);
        }
        query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        return list;
    }

    public void executeBySql(String sql) {
        this.getSession().createSQLQuery(sql).executeUpdate();
    }

    public void executeProc(String proName, List<String> params) {

        Connection connection=null;
        try {
             connection = getConnection();
            CallableStatement proc = connection.prepareCall("{ call " + proName
                    + " }");
            for (int i = 0; i < params.size(); i++) {
                proc.setString(i + 1, params.get(i));
            }
            proc.execute();
        } catch (Exception e) {
        }finally {
            try {
               if(connection!=null){
                   connection.close();
               }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public void flush() {
        getSession().flush();

    }

    public String getClobBySql(String sql) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getBlobStrBySql(String sql) {
        // TODO Auto-generated method stub
        return null;
    }

    public void updateBlobBySql(String sql, String content) {
        // TODO Auto-generated method stub

    }

    public void updateLongraw(String sql, String content) {
        // TODO Auto-generated method stub

    }

    public String getLongrawBySql(String sql) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean rollback() {
        // TODO Auto-generated method stub
        return false;
    }

    public <T> List<T> getListfromSql(Connection conn, String sql, Class<T> T) {
        Connection c;
        if (conn == null) {
            c = getConnection();
        } else {
            c = conn;
        }
        try {
            return assemble(c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql), T, mappingColumnsWithOutAnnotation(T));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
               if(conn==null){
                   c.close();
               }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public  <T> List<T> assemble(ResultSet rs, Class<?> T, Map columnsMap) {
        //long start = new java.util.Date().getTime();
        List<T> list =null;
        try{
            rs.beforeFirst();
            Map<String, String> columns = columnsMap;
            Object[][] structs = null;
            int columnCount = 0;
            if (rs.next()) {
                list= new ArrayList<T>();
                Object o = T.newInstance();
                ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
                structs = new Object[md.getColumnCount()][2];
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    String columnName = md.getColumnLabel(i).toLowerCase();
                    String fieldName = columns.get(columnName);
                    columns.remove(columnName);
                    if (fieldName != null) {
                        Field field = T.getDeclaredField(fieldName);
                        Class<?> FieldType = field.getType();
                        String setMethod = "set"
                                + fieldName.substring(0, 1).toUpperCase()
                                + fieldName.substring(1);
                        Method method = T.getDeclaredMethod(setMethod,
                                new Class[]{FieldType});
                        if (rs.getObject(i) != null) {
                            method.invoke(o, new Object[]{rs.getObject(i)});
                        }

                        structs[columnCount][0] = i;
                        structs[columnCount++][1] = method;
                    }
                }
                list.add((T) o);
            }
            while (rs.next()) {
                Object o = T.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Method m = (Method) (structs[i][1]);
                    Object value = rs.getObject((Integer) structs[i][0]);
                    if (value != null) {
                        m.invoke(o, value);
                    }

                }
                list.add((T) o);
            }
        }catch(Exception e){e.printStackTrace();}finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //long end = new java.util.Date().getTime();
        //System.out.println(end - start);
        return list;
    }

    public  Map<String, String> mappingColumnsWithOutAnnotation(Class c) {
        Map<String, String> map = new HashMap<String, String>();
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            map.put(f.getName().toLowerCase(), f.getName());
        }
        return map;
    }

    public Long getCountBySQL(String sql, List values)
    {
      Query query = getSession().createSQLQuery(sql);
      if (values != null) {
        for (int i = 0; i < values.size(); i++) {
          query.setParameter(i, values.get(i));
        }
      }
      query.setMaxResults(1);
      Object c = query.uniqueResult();
      if (c == null)
        return Long.valueOf(0L);
      if ((c instanceof BigDecimal)) {
        return Long.valueOf(((BigDecimal)c).longValue());
      }
      return (Long)c;
    }
    public Long getCountByHQL(String hql, List values)
    {
      Query query = getSession().createQuery(hql);
      if (values != null) {
        for (int i = 0; i < values.size(); i++) {
          query.setParameter(i, values.get(i));
        }
      }
      query.setMaxResults(1);

      Object c = query.uniqueResult();
      if (c == null)
        return Long.valueOf(0L);
      if ((c instanceof BigDecimal)) {
        return Long.valueOf(((BigDecimal)c).longValue());
      }
      return (Long)c;
    }
}
