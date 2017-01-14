package com.wittymonkey.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public interface IGenericDao<T, PK extends Serializable> {
	/**
	 * 通过id获取对象
	 * @param id
	 * @return
	 */
	public Object load(PK id);
	
	/**
	 * 获取session
	 * @return
	 */
	public Session getCurrentSession();
	
	public void clear();

	public Object save(T t);
	
	public void  delete(T t) throws SQLException;
	
	public void  update(T t) throws SQLException;
	
	/**
	 * 无条件的查询所有对象
	 * @return
	 */
    public List<T> findAll();
    
    
    
    public List<T> findAll(T t);
     
    public int  batchDelete(List<Integer>  ids) ;
    
    /**
     * 批量更新
     * @param list
     * @return
     */
    public boolean batchUpdate(List<T> list);
    
    /**
     * 根据hql语句进行条件查询
     * @param hql
     * @param map
     * @return
     */
    public  List<T>   queryHQL(String hql,Map<String,?> map);
    
    /**
     *  根据sql查询
     * @param hql
     * @return
        2017年1月5日下午2:57:08
     */
    public List<Map<String,Object>>  querySQL(String Sql);
}
