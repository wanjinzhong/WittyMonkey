package com.wittymonkey.dao.impl;

import com.wittymonkey.dao.IOdomDao;
import com.wittymonkey.entity.Odom;
import org.springframework.stereotype.Repository;

/**
 * Created by neilw on 2017/4/22.
 */
@Repository(value = "odomDao")
public class OdomDaoImpl extends GenericDaoImpl<Odom> implements IOdomDao {
}
