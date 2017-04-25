package com.wittymonkey.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by neilw on 2017/4/20.
 */
public class ConditionModel {
    private String hql = "";
    private Map<String, Object> param = new HashMap<String, Object>();

    public String getHql() {
        return hql;
    }

    public void setHql(String hql) {
        this.hql = hql;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }
}
