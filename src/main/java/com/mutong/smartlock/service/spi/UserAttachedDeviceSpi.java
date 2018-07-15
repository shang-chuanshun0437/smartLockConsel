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
    public List<UserAttachedDeviceInfo> findByPhoneNum(String phoneNum)
    {
        return userAttachedDevice.findByPhoneNum(phoneNum);
    }

    @Override
    public List<UserAttachedDeviceInfo> findByDeviceNum(String deviceNUm)
    {
        return userAttachedDevice.findByDeviceNum(deviceNUm);
    }

    @Override
    public UserAttachedDeviceInfo findByDeviceNumAndPhoneNum(String deviceNum,String phoneNum)
    {
        return userAttachedDevice.findByDeviceNumAndPhoneNum(deviceNum,phoneNum);
    }

    @Override
    public UserAttachedDeviceInfo save(UserAttachedDeviceInfo userAttachedDeviceInfo)
    {
        return userAttachedDevice.save(userAttachedDeviceInfo);
    }

    @Override
    public List<UserAttachedDeviceInfo> findByPhoneNumOrMainUser(String phoneNum,String mainUser) {
        return userAttachedDevice.findByPhoneNumOrMainUser(phoneNum,mainUser);
    }
}
