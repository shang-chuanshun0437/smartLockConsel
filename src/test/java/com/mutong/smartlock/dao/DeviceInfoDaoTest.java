package com.mutong.smartlock.dao;

import com.mutong.smartlock.dao.entity.DeviceInfo;
import com.mutong.smartlock.dao.jpa.DeviceInfoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceInfoDaoTest {
    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @Test
    public void save() {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setBluetoothMac("ee:erqq");
        deviceInfo.setDeviceNum("ff");
        deviceInfo.setCreateTime("20180530");
        deviceInfo.setPhoneNum("rrfftyeeff");

        deviceInfoDao.save(deviceInfo);
    }

    @Test
    public void findOne()
    {
        DeviceInfo deviceInfo = deviceInfoDao.findByDeviceNum("ff");
        System.out.println(deviceInfo.toString());
    }
}