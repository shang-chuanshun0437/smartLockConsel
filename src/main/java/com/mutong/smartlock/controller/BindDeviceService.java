package com.mutong.smartlock.controller;

import com.mutong.smartlock.controller.request.BindDeviceRequest;
import com.mutong.smartlock.controller.response.BindDeviceResponse;

public interface BindDeviceService
{
    BindDeviceResponse bindDevice(BindDeviceRequest request);
}
