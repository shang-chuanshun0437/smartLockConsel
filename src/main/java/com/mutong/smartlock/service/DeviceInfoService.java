package com.mutong.smartlock.service;

import com.mutong.smartlock.dao.entity.DeviceInfo;

import java.util.List;

public interface DeviceInfoService
{
    DeviceInfo findByDeviceNum(String deviceNum);

    List<DeviceInfo> findAll();

    List<DeviceInfo> findByDeviceNumLike(String deviceNum);

    DeviceInfo save(DeviceInfo deviceInfo);
}
