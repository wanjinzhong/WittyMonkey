package com.wittymonkey.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface IGenericDao<T, PK extends Serializable> {
    /**
     * 通过id获取对象
     *
     * @param id
     * @return
     */
    Object load(PK id);

    /**
     * 获取session
     *
     * @return
     */
    Session getCurrentSession();

    void clear();

    Object save(T t);

    void delete(T t) throws SQLException;

    void update(T t) throws SQLException;

    /**
     * 无条件的查询所有对象
     *
     * @return
     */
    List<T> findAll();


    List<T> findAll(T t);

    int batchDelete(List<Integer> ids);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    boolean batchUpdate(List<T> list);

    /**
     * 根据hql语句进行条件查询
     *
     * @param hql
     * @param
     * @return
     */
    List<T> queryListHQL(String hql, Object obj);

    List<T> queryListHQL(String hql, Object obj, Integer first, Integer total);

    T queryOneHql(String hql, Object obj);

    /**
     * 根据sql查询
     *
     * @param
     * @return 2017年1月5日下午2:57:08
     */
    List<T> queryListSQL(String Sql, Object object);

    List<T> queryListSQL(String sql, Object obj, Integer first, Integer total);

    T queryOneSQL(String sql, Object object);


    Integer executeSQL(String sql, Object object);

    Integer countHQL(String hql, Object object);

    Integer countSQL(String sql, Object object);

}
