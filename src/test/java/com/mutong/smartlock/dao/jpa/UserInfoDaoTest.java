package com.mutong.smartlock.dao.jpa;

import com.mutong.smartlock.dao.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoDaoTest
{
    @Autowired
    UserInfoDao userInfoDao;

    @Test
    public void saveTest()
    {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("2322343322");
        userInfo.setPassword("fowjft24yrws");
        userInfo.setCreateTime("3902823");

        userInfoDao.save(userInfo);
    }
    @Test
    public void findByUserName()
    {
        UserInfo userInfo = userInfoDao.findByUserName("2839329922");
        //System.out.println(userInfo.toString());
    }

    @Test
    public void findAll()
    {
        List<UserInfo> userInfos =  userInfoDao.findAll();
        for (UserInfo userInfo : userInfos)
        {
            System.out.println(userInfo.toString());
        }
    }

    @Test
    public void findByUserNameLike() {
    }

    @Test
    public void deleteTest()
    {
        int userInfo = userInfoDao.deleteByUserName("2839329922");
        System.out.println(userInfo);
    }
}