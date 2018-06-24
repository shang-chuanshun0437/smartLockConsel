package com.mutong.smartlock.service;

import com.mutong.smartlock.controller.request.BindDeviceRequest;
import com.mutong.smartlock.controller.request.QueryUserAttachedDeviceRequest;
import com.mutong.smartlock.controller.response.BindDeviceResponse;
import com.mutong.smartlock.controller.response.QueryUserAttachedDeviceRespose;

public interface UserAttachedDeviceManagerService
{
    BindDeviceResponse bindDevice(BindDeviceRequest request);

    QueryUserAttachedDeviceRespose queryUserAttachedDevice(QueryUserAttachedDeviceRequest request);
}
