package com.mutong.smartlock.service.spi;

import com.mutong.smartlock.dao.entity.UserAttachedDeviceInfo;
import com.mutong.smartlock.dao.jpa.UserAttachedDeviceDao;
import com.mutong.smartlock.service.UserAttachedDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAttachedDeviceSpi implements UserAttachedDeviceService
{
    @Autowired
    private UserAttachedDeviceDao userAttachedDevice;

    @Override
    public List<UserAttachedDeviceInfo> findByUserName(String userName)
    {
        return userAttachedDevice.findByUserName(userName);
    }

    @Override
    public List<UserAttachedDeviceInfo> findByDeviceNum(String deviceNUm)
    {
        return userAttachedDevice.findByDeviceNum(deviceNUm);
    }

    @Override
    public UserAttachedDeviceInfo findByDeviceNumAndUserName(String deviceNum,String userName)
    {
        return userAttachedDevice.findByDeviceNumAndUserName(deviceNum,userName);
    }

    @Override
    public UserAttachedDeviceInfo save(UserAttachedDeviceInfo userAttachedDeviceInfo)
    {
        return userAttachedDevice.save(userAttachedDeviceInfo);
    }

    @Override
    public List<UserAttachedDeviceInfo> findByUserNameOrMainUser(String userName,String mainUser) {
        return userAttachedDevice.findByUserNameOrMainUser(userName,mainUser);
    }
}
