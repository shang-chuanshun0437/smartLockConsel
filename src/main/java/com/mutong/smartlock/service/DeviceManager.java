package com.mutong.smartlock.service;

import com.mutong.smartlock.controller.response.ModifyDeviceNameResponse;
import com.mutong.smartlock.dao.entity.DeviceInfo;
import com.mutong.smartlock.service.request.GetDeviceInfoRequest;
import com.mutong.smartlock.service.response.GetDeviceInfoResponse;

public interface DeviceManager
{
    GetDeviceInfoResponse findDeviceByNum(String deviceNum);

    ModifyDeviceNameResponse modifyDeviceName(String deviceNum,String newName);
}
