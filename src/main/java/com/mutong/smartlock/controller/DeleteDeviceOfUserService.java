package com.mutong.smartlock.controller;

import com.mutong.smartlock.controller.request.DeleteDeviceOfUserRequest;
import com.mutong.smartlock.controller.response.DeleteDeviceOfUserResponse;

public interface DeleteDeviceOfUserService
{
    DeleteDeviceOfUserResponse deleteDevice(DeleteDeviceOfUserRequest request);
}
