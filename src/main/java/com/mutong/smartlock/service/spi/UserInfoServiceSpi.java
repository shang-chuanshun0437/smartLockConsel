package com.mutong.smartlock.service.spi;

import com.mutong.smartlock.dao.entity.UserInfo;
import com.mutong.smartlock.dao.jpa.UserInfoDao;
import com.mutong.smartlock.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceSpi implements UserInfoService
{
    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public UserInfo findByPhoneNum(String phoneNum)
    {
        return userInfoDao.findByPhoneNum(phoneNum);
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }

}
