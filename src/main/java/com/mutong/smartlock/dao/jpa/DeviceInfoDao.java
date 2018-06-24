package com.mutong.smartlock.dao.jpa;

import com.mutong.smartlock.dao.entity.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceInfoDao extends JpaRepository<DeviceInfo,String>
{
    DeviceInfo findByDeviceNum(String deviceNum);

    @Override
    List<DeviceInfo> findAll();

    List<DeviceInfo> findByDeviceNumLike(String deviceNum);
}
