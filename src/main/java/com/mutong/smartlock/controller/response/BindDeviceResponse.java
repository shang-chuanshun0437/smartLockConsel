package com.mutong.smartlock.controller.response;

import com.mutong.smartlock.common.Result;

public class BindDeviceResponse
{
    private Result result;

    private String bluetoothMac;

    private String attachedTime;

    private String deviceVersion;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getAttachedTime() {
        return attachedTime;
    }

    public void setAttachedTime(String attachedTime) {
        this.attachedTime = attachedTime;
    }

    public String getBluetoothMac() {
        return bluetoothMac;
    }

    public void setBluetoothMac(String bluetoothMac) {
        this.bluetoothMac = bluetoothMac;
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion;
    }
}
