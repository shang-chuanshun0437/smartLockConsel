package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ModifyDeviceNameRequest
{
    @NotBlank(message = "phoneNum can not be null")
    @Size(min = 11,max = 11)
    private String phoneNum;

    @NotBlank(message = "token can not be null")
    private String token;

    @NotBlank(message = "deviceNum can not be null")
    @Size(min = 11,max = 11)
    private String deviceNum;

    @NotBlank(message = "deviceName can not be null")
    private String deviceName;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String toString() {
        return "ModifyDeviceNameRequest{" +
                "phoneNum='" + phoneNum + '\'' +
                ", deviceNum='" + deviceNum + '\'' +
                ", deviceName='" + deviceName + '\'' +
                '}';
    }
}
