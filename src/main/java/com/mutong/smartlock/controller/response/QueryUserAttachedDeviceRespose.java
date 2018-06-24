package com.mutong.smartlock.controller.response;

import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.service.common.UserAttachedDevice;

public class QueryUserAttachedDeviceRespose
{
    private Result result;

    private UserAttachedDevice[] userAttachedDevices;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public UserAttachedDevice[] getUserAttachedDevices() {
        return userAttachedDevices;
    }

    public void setUserAttachedDevices(UserAttachedDevice[] userAttachedDevices) {
        this.userAttachedDevices = userAttachedDevices;
    }
}
