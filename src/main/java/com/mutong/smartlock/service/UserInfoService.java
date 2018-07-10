package com.mutong.smartlock.service;

import com.mutong.smartlock.dao.entity.UserInfo;

import java.util.List;

public interface UserInfoService
{
    UserInfo findByPhoneNum(String phoneNum);

    UserInfo save(UserInfo userInfo);

}
