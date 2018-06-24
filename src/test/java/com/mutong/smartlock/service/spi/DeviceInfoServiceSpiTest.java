package com.mutong.smartlock.service.spi;

import com.mutong.smartlock.dao.entity.DeviceInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceInfoServiceSpiTest {

    @Autowired
    private DeviceInfoServiceSpi deviceInfoServiceSpi;
    @Test
    public void findByDeviceNum()
    {
        DeviceInfo deviceInfo = deviceInfoServiceSpi.findByDeviceNum("00000000000");
        System.out.println(deviceInfo.toString());
    }

    @Test
    public void findAll()
    {
        List<DeviceInfo> deviceInfos = deviceInfoServiceSpi.findAll();
        for (DeviceInfo deviceInfo : deviceInfos) {
            System.out.println(deviceInfo.toString());
        }
    }

    @Test
    public void findByDeviceNumLike()
    {
        List<DeviceInfo> deviceInfos = deviceInfoServiceSpi.findByDeviceNumLike("ww");
        for (DeviceInfo deviceInfo : deviceInfos) {
            System.out.println(deviceInfo.toString());
        }
    }
}