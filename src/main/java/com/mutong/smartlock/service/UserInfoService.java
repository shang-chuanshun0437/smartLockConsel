package com.mutong.smartlock.service;

import com.mutong.smartlock.dao.entity.UserInfo;

import java.util.List;

public interface UserInfoService
{
    UserInfo findByUserName(String userName);

    List<UserInfo> findAll();

    List<UserInfo> findByUserNameLike(String userNam);

    UserInfo save(UserInfo userInfo);

    int deleteByUserName(String userName);
}
