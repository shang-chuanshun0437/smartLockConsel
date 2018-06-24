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
    public UserInfo findByUserName(String userName) {
        return userInfoDao.findByUserName(userName);
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }

    @Override
    public List<UserInfo> findByUserNameLike(String userNam) {
        return userInfoDao.findByUserNameLike(userNam);
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        return userInfoDao.save(userInfo);
    }

    @Override
    public int deleteByUserName(String userName) {
        return userInfoDao.deleteByUserName(userName);
    }

}
