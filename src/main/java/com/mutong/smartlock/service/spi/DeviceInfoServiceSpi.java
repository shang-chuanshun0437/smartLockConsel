package com.mutong.smartlock.service.spi;

import com.mutong.smartlock.dao.jpa.DeviceInfoDao;
import com.mutong.smartlock.dao.entity.DeviceInfo;
import com.mutong.smartlock.service.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceInfoServiceSpi implements DeviceInfoService
{
    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @Override
    public DeviceInfo findByDeviceNum(String deviceNum) {
        return deviceInfoDao.findByDeviceNum(deviceNum);
    }

    @Override
    public List<DeviceInfo> findAll() {
        return deviceInfoDao.findAll();
    }

    @Override
    public List<DeviceInfo> findByDeviceNumLike(String deviceNum) {
        return deviceInfoDao.findByDeviceNumLike(deviceNum);
    }

    @Override
    public DeviceInfo save(DeviceInfo deviceInfo)
    {
        return deviceInfoDao.save(deviceInfo);
    }
}
