package com.mutong.smartlock.dao.jpa;

import com.mutong.smartlock.dao.entity.UserAttachedDeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAttachedDeviceDao extends JpaRepository<UserAttachedDeviceInfo,Integer>
{
    @Override
    List<UserAttachedDeviceInfo> findAll();

    List<UserAttachedDeviceInfo> findByPhoneNum(String phoneNum);

    List<UserAttachedDeviceInfo> findByDeviceNum(String deviceNum);

    UserAttachedDeviceInfo findByDeviceNumAndPhoneNum(String deviceNum,String phoneNum);

    int deleteByUserName(String userName);

    List<UserAttachedDeviceInfo> findByPhoneNumOrMainUser(String phone,String mainUser);
}
