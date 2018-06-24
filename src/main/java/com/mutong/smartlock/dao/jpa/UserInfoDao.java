package com.mutong.smartlock.dao.jpa;

import com.mutong.smartlock.dao.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoDao extends JpaRepository<UserInfo,String>
{
    UserInfo findByUserName(String userName);

    @Override
    List<UserInfo> findAll();

    List<UserInfo> findByUserNameLike(String userNam);

    int deleteByUserName(String userName);
}
