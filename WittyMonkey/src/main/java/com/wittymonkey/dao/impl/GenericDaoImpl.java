package com.wittymonkey.dao.impl;

import com.wittymonkey.dao.IGenericDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

public class GenericDaoImpl<T> implements IGenericDao<T, Serializable> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<?> entityClass;

    public Object load(Serializable id) {
        //load要懒加载，这里用get
        return this.getCurrentSession().get(entityClass, id);
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
        if (object instanceof Map) {
            setParam(query, (Map) object);
        } else {
            setParam(query, object);
        }
        List<T> result = query.list();
        return result;
    }

    @Override
    public List<T> queryListHQL(String hql, Object obj, Integer first, Integer total) {
        Session session = this.getCurrentSession();
        Query query = session.createQuery(hql);
        if (obj instanceof Map) {
            setParam(query, (Map) obj);
        } else {
            setParam(query, obj);
        }
        if (first != null && total != null) {
            query.setFirstResult(first);
            query.setMaxResults(total);
        }

        List<T> result = query.list();
        return result;
    }

    public void setParam(Query query, Map<String, ?> params) {
        Iterator<?> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Entry<String, ?> entry = (Entry<String, ?>) iterator.next();
            Object obj = entry.getValue();
            if (obj instanceof String) {
                query.setString(entry.getKey(), (String) entry.getValue());
            } else if (obj instanceof Integer) {
                query.setInteger(entry.getKey(), (Integer) entry.getValue());
            } else if (obj instanceof Double) {
                query.setDouble(entry.getKey(), (Double) entry.getValue());
            } else if (obj instanceof Date) {
                query.setDate(entry.getKey(), (Date) entry.getValue());
            } else if (obj instanceof Boolean) {
                query.setBoolean(entry.getKey(), (Boolean) entry.getValue());
            }

        }
    }

    public void setParam(Query query, Object obj) {
        if (obj instanceof String) {
            query.setString(0, (String) obj);
        } else if (obj instanceof Integer) {
            query.setInteger(0, (Integer) obj);
        } else if (obj instanceof Double) {
            query.setDouble(0, (Double) obj);
        } else if (obj instanceof Date) {
            query.setDate(0, (Date) obj);
        } else if (obj instanceof Boolean) {
            query.setBoolean(0, (Boolean) obj);
        }
    }


    @Override
    public T queryOneHql(String hql, Object obj) {
        List<T> list = queryListHQL(hql, obj);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    @Override
    public List<T> queryListSQL(String sql, Object object) {
        Session session = this.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        if (object instanceof Map) {
            setParam(query, (Map) object);
        } else {
            setParam(query, object);
        }
        query.addEntity(entityClass);
        return query.list();
    }

    @Override
    public List<T> queryListSQL(String sql, Object obj, Integer first, Integer total) {
        Session session = this.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        if (obj instanceof Map) {
            setParam(query, (Map) obj);
        } else {
            setParam(query, obj);
        }
        query.setFirstResult(first);
        query.setMaxResults(total);
        query.addEntity(entityClass);
        return query.list();
    }

    @Override
    public T queryOneSQL(String sql, Object object) {
        List<T> list = queryListSQL(sql, object);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Integer executeSQL(String sql, Object obj) {
        Integer result;
        SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
        if (obj instanceof Map) {
            setParam(query, (Map) obj);
        } else {
            setParam(query, obj);
        }
        result = query.executeUpdate();
        return result;
    }

    @Override
    public Integer countHQL(String hql, Object obj) {
        Session session = this.getCurrentSession();
        Query query = session.createQuery(hql);
        if (obj instanceof Map) {
            setParam(query, (Map) obj);
        } else {
            setParam(query, obj);
        }
        return ((Number) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countSQL(String sql, Object obj) {
        Session session = this.getCurrentSession();
        Query query = session.createSQLQuery(sql);
        if (obj instanceof Map) {
            setParam(query, (Map) obj);
        } else {
            setParam(query, obj);
        }
        String result = query.uniqueResult().toString();
        return ((Number) query.uniqueResult()).intValue();
    }

    @Override
    public List<T> findAll(T t) {
        return null;
    }
}
