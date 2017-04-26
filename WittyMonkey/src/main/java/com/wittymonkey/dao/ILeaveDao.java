package com.wittymonkey.dao;

import com.wittymonkey.entity.Leave;

import java.io.Serializable;
import java.util.List;

public interface ILeaveDao extends IGenericDao<Leave, Serializable>{

    List<Leave> getLeaveByType(Integer typeId);
}
