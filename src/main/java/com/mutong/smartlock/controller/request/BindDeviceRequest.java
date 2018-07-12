package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BindDeviceRequest
{
    @NotBlank(message = "user name can not be null")
    @Size(min = 11,max = 11)
    //待绑定的手机号
    private String phoneNum;

    private String token;

    @NotBlank(message = "device num can not be null")
    @Size(min = 11,max = 11)
    //待绑定的设备编号
    private String deviceNum;

    //设备名称
    private String deviceName;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "BindDeviceRequest{" +
                "phoneNum='" + phoneNum + '\'' +
                ", token='" + token + '\'' +
                ", deviceNum='" + deviceNum + '\'' +
                ", deviceName='" + deviceName + '\'' +
                '}';
    }
}
