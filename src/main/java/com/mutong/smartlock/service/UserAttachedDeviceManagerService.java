package com.mutong.smartlock.service;

import com.mutong.smartlock.controller.request.BindDevice4UserRequest;
import com.mutong.smartlock.controller.request.BindDeviceRequest;
import com.mutong.smartlock.controller.request.QueryUserAttachedDeviceRequest;
import com.mutong.smartlock.controller.response.BindDevice4UserResponse;
import com.mutong.smartlock.controller.response.BindDeviceResponse;
import com.mutong.smartlock.controller.response.QueryUserAttachedDeviceRespose;
import com.mutong.smartlock.dao.entity.DeviceInfo;

public interface UserAttachedDeviceManagerService
{
    BindDeviceResponse bindDevice(BindDeviceRequest request);

    QueryUserAttachedDeviceRespose queryUserAttachedDevice(QueryUserAttachedDeviceRequest request);

    BindDevice4UserResponse bindDevice4User(DeviceInfo deviceInfo, String bindPhoneNum,String validDate);
}
