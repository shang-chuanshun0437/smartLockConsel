package com.mutong.smartlock.controller;

import com.mutong.smartlock.controller.request.ModifyDeviceNameRequest;
import com.mutong.smartlock.controller.request.ModifyUserNameRequest;
import com.mutong.smartlock.controller.response.ModifyDeviceNameResponse;
import com.mutong.smartlock.controller.response.ModifyUserNameResponse;

public interface ModifyDeviceNameService
{
    ModifyDeviceNameResponse modifyDeviceName(ModifyDeviceNameRequest request);
}
