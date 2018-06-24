package com.mutong.smartlock.dao.jpa;

import com.mutong.smartlock.dao.entity.UserAttachedDeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAttachedDeviceDao extends JpaRepository<UserAttachedDeviceInfo,Integer>
{
    @Override
    List<UserAttachedDeviceInfo> findAll();

    List<UserAttachedDeviceInfo> findByUserName(String userName);

    List<UserAttachedDeviceInfo> findByDeviceNum(String deviceNum);

    UserAttachedDeviceInfo findByDeviceNumAndUserName(String deviceNum,String userName);

    int deleteByUserName(String userName);

    List<UserAttachedDeviceInfo> findByUserNameOrMainUser(String userName,String mainUser);
}
