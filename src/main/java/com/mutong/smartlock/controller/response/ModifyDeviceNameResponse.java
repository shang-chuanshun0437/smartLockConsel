package com.mutong.smartlock.controller.response;

import com.mutong.smartlock.common.Result;

public class ModifyDeviceNameResponse
{
    private Result result;

    private String deviceName;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
