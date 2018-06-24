package com.mutong.smartlock.controller;

import com.mutong.smartlock.service.response.GetDeviceInfoResponse;

public interface GetDeviceInfoService
{
    GetDeviceInfoResponse getAllDeviceInfo();

    GetDeviceInfoResponse getDevicInfoByDeviceNum(long deviceNum);

    //根据设备编号进行模糊查询
    GetDeviceInfoResponse getDeviceInfoBylike(long deviceNum);

}
