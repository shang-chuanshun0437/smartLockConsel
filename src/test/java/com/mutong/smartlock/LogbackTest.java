package com.mutong.smartlock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogbackTest
{
    private static final Logger logger = LoggerFactory.getLogger(LogbackTest.class);

    @Test
    public void test1()
    {
        String msg = "msg";
        logger.debug("debug -----{}",msg);
        logger.info("info -----{}",msg);
        logger.error("error ----{}",msg);
    }
    @Test
    public void dateTest()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        // 利用一个字符串获取得到的日期
        String s = sdf.format(date);
        // 输出
        System.out.println(s);
    }
    @Test
    public void dateTest2()
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        // 利用一个字符串获取得到的日期
        String s = sdf.format(date);
        // 输出
        System.out.println(s);
    }
    @Test
    public void uuidTest()
    {
        System.out.println(UUID.randomUUID());
    }
}
