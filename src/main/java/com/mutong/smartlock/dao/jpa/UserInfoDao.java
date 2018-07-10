package com.mutong.smartlock.dao.jpa;

import com.mutong.smartlock.dao.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoDao extends JpaRepository<UserInfo,String>
{
    UserInfo findByPhoneNum(String phoneNum);

    @Override
    List<UserInfo> findAll();

}
