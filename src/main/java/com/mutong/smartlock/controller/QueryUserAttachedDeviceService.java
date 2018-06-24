package com.mutong.smartlock.controller;

import com.mutong.smartlock.controller.request.QueryUserAttachedDeviceRequest;
import com.mutong.smartlock.controller.response.QueryUserAttachedDeviceRespose;

public interface QueryUserAttachedDeviceService
{
    QueryUserAttachedDeviceRespose queryUserDevices(QueryUserAttachedDeviceRequest request);
}
