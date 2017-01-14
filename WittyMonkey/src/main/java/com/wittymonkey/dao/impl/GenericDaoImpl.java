package com.wittymonkey.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wittymonkey.dao.IGenericDao;
import com.wittymonkey.util.Reflections;

public class GenericDaoImpl<T> implements IGenericDao<T, Serializable> {

	@Autowired
	private SessionFactory sessionFactory;

	private Class<?> entityClass;

	public GenericDaoImpl() {
		entityClass = Reflections.getClassGenricType(getClass());
	}
	
	@Override
	public Object load(Serializable id) {
		// TODO Auto-generated method stub
		System.out.println(entityClass == null);
		System.out.println(id == null);
		return this.getCurrentSession().get(entityClass, id);
	}
	
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object save(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(T t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(T t) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int batchDelete(List<Integer> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean batchUpdate(List<T> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<T> queryHQL(String hql, Map<String, ?> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> querySQL(String Sql) {
		// TODO Auto-generated method stub
		return null;
	}

}
