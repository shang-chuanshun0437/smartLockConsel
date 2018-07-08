package com.mutong.smartlock.dao.jpa;

import com.mutong.smartlock.dao.entity.OpenDoorHistoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpenDoorHistoryDao extends JpaRepository<OpenDoorHistoryInfo,Integer>
{
    List<OpenDoorHistoryInfo> findByDeviceNum(String deviceNum);
}
