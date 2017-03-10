package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IChangeRoomDao;
import com.wittymonkey.entity.ChangeRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IChangeRoomService;

@Service(value="changeRoomService")
public class ChangeRoomServiceImpl implements IChangeRoomService{

    @Autowired
    private IChangeRoomDao changeRoomDao;

    @Override
    public void save(ChangeRoom changeRoom) {
        changeRoomDao.save(changeRoom);
    }
}
