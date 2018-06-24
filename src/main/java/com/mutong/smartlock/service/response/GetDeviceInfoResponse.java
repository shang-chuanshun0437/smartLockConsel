package com.mutong.smartlock.service.response;

import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.dao.entity.DeviceInfo;

public class GetDeviceInfoResponse
{
    private Result result;

    private DeviceInfo deviceInfo;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
