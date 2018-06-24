package com.mutong.smartlock.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
    //把当前时间格式化为：yyyyMMddHHmm
    public static String yyyyMMddHHmm()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

        return sdf.format(new Date());
    }

    //把当前时间格式化为：yyyy年MM月dd日HH：mm：ss
    public static String yyyyMMddHHmmss()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

        return sdf.format(new Date());
    }
}
