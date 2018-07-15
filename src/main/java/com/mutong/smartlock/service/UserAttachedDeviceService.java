package com.mutong.smartlock.service;

import com.mutong.smartlock.dao.entity.UserAttachedDeviceInfo;
import com.mutong.smartlock.dao.jpa.UserAttachedDeviceDao;

import java.util.List;

public interface UserAttachedDeviceService
{
    List<UserAttachedDeviceInfo> findByPhoneNum(String phoneNum);

    List<UserAttachedDeviceInfo> findByDeviceNum(String deviceNum);

    UserAttachedDeviceInfo findByDeviceNumAndPhoneNum(String deviceNum,String phoneNum);

    UserAttachedDeviceInfo save(UserAttachedDeviceInfo userAttachedDeviceInfo);

    List<UserAttachedDeviceInfo> findByPhoneNumOrMainUser(String phoneNum,String mainUser);
}
