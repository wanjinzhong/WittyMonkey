package com.wittymonkey.dao.impl;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.wittymonkey.dao.IGenericDao;
import com.wittymonkey.util.Reflections;

public class GenericDaoImpl<T> implements IGenericDao<T, Serializable> {

	@Autowired
	private SessionFactory sessionFactory;

	private Class<?> entityClass;

	public Object load(Serializable id) {
		//load要懒加载，这里用get
		return  this.getCurrentSession().get(entityClass, id);
	}

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void clear() {
		getCurrentSession().clear();
	}

	public Object save(T t) {
		return this.getCurrentSession().merge(t);
	}

	public void delete(T t) {
		this.getCurrentSession().delete(t);
	}

	public void update(T t) {
		this.getCurrentSession().update(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return this.getCurrentSession().createCriteria(entityClass).list();
	}

	/*/**
	 * 查询条件的绑定
	 * 
	 * @param t
	 * @param map
	 * @return
	 */
	/*private Criteria bindQuery(T t, Map<String, MatchType> map) {
		Criteria criteria = this.getCurrentSession()
				.createCriteria(entityClass);
		Map<String, Object> params = JsonUtil.convertFieldKeyValue(t);
		Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			if (entry.getValue() != null) {
				if (map.get(key) == MatchType.MATCH_LIKE) {
					criteria.add(Restrictions.like(entry.getKey(),
							entry.getValue()));
				} else if (map.get(key) == MatchType.MATCH_GT) {
					criteria.add(Restrictions.gt(entry.getKey(),
							entry.getValue()));
				} else if (map.get(key) == MatchType.MATCH_LT) {
					criteria.add(Restrictions.lt(entry.getKey(),
							entry.getValue()));
				} else {
					// 默认eq
					criteria.add(Restrictions.eq(entry.getKey(),
							entry.getValue()));
				}

			}
		}
		return criteria;
	}*/

	/*private Criteria bindQuery(T t) {
		Criteria criteria = this.getCurrentSession()
				.createCriteria(entityClass);
		Map<String, Object> params = JsonUtil.convertFieldKeyValue(t);
		Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			if (this.isEmpty(entry.getValue())) {
				criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			}
		}
		return criteria;
	}*/

	/*/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 * @return
	 */
	/*private boolean isEmpty(Object obj) {
		if (obj instanceof String) {
			if (obj != null)
				return true;
		} else if (obj instanceof Integer) {
			if ((Integer) obj != 0)
				return true;
		} else if (obj instanceof Set) {
			if (!((Set) obj).isEmpty())
				return true;
		}
		return false;
	}*/


	/*public List<T> findAll(T t) {
		Criteria criteria = this.bindQuery(t);
		List<T> result = criteria.list();
		return result;
	}*/

	/*public List<T> findAll(T t, Map<String, MatchType> map) {
		Criteria criteria = this.bindQuery(t, map);
		@SuppressWarnings("unchecked")
		List<T> result = criteria.list();
		return result;
	}*/

	/*/**
	 * 查询总条数
	 * 
	 * @param t
	 *            :查询的具体条件
	 * @param map
	 *            criteria的查询条件描述
	 * @return
	 */
	/*protected long count(T t, Map<String, MatchType> map) {
		Criteria criteria = this.bindQuery(t, map);
		criteria.setProjection(Projections.rowCount());
		Integer count = (Integer) criteria.uniqueResult();
		return count;
	}*/

	/*public Page<T> findByPage(T t, Page<T> page, Map<String, MatchType> map) {
		long count = this.count(t, map);
		page.setCount(count);
		Criteria criteria = this.bindQuery(t, map);
		criteria.setFirstResult(page.getNextData());
		criteria.setMaxResults(page.getPageSize());
		@SuppressWarnings("unchecked")
		List<T> list = criteria.list();
		page.setResult(list);
		return page;
	}*/

	public int batchDelete(List<Integer> ids) {
		StringBuilder hql = new StringBuilder("delete from " + entityClass
				+ " where id in (:ids)");
		Query query = this.getCurrentSession().createQuery(hql.toString())
				.setParameterList("ids", ids);
		return query.executeUpdate();
	}

	public boolean batchUpdate(List<T> list) {
		return false;
	}

	@Override
	public List<T> queryListHQL(String hql, Object object) {
		Session session = this.getCurrentSession();
		Query query = session.createQuery(hql);
		if (object != null){
			Map<String, ?> params = (Map<String, ?>) object;
			Iterator<?> iterator = params.entrySet().iterator();
			while (iterator.hasNext()) {
				@SuppressWarnings("unchecked")
				Entry<String, ?> entry = (Entry<String, ?>) iterator.next();
				Object obj = entry.getValue();
				if (obj instanceof String) {
					query.setString(entry.getKey(), (String) entry.getValue());
				} else if (obj instanceof Integer) {
					query.setInteger(entry.getKey(), (Integer) entry.getValue());
				} else if (obj instanceof Double){
					query.setDouble(entry.getKey(), (Double) entry.getValue());
				} else if (obj instanceof Date) {
					query.setDate(entry.getKey(), (Date) entry.getValue());
				}

			}
		}
		
		List<T> result = query.list();
		return result;
	}

	@Override
	public T queryOneHql(String hql, Object obj) {
		List<T> list = queryListHQL(hql, obj);
		if (list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public List<Map<String,Object>> querySQL(String sql) {
		Session session = this.getCurrentSession();
		//将结果化为map的形式
		 Query  query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public List<T> findAll(T t) {
		return null;
	}
}
