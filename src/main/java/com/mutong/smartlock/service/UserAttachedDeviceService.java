package com.mutong.smartlock.service;

import com.mutong.smartlock.dao.entity.UserAttachedDeviceInfo;
import com.mutong.smartlock.dao.jpa.UserAttachedDeviceDao;

import java.util.List;

public interface UserAttachedDeviceService
{
    List<UserAttachedDeviceInfo> findByUserName(String userName);

    List<UserAttachedDeviceInfo> findByDeviceNum(String deviceNum);

    UserAttachedDeviceInfo findByDeviceNumAndUserName(String deviceNum,String userName);

    UserAttachedDeviceInfo save(UserAttachedDeviceInfo userAttachedDeviceInfo);

    List<UserAttachedDeviceInfo> findByUserNameOrMainUser(String userName,String mainUser);
}
