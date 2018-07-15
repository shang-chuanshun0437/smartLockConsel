package com.mutong.smartlock.dao.jpa;

import com.mutong.smartlock.dao.entity.UserAttachedDeviceInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserAttachedDeviceDaoTest {

    @Autowired
    UserAttachedDeviceDao userAttachedDeviceDao;
    @Test
    public void findByUserNameOrMainUser()
    {
        List<UserAttachedDeviceInfo> userAttachedDeviceInfos = userAttachedDeviceDao.findByPhoneNumOrMainUser("18753137390","");
    }
}