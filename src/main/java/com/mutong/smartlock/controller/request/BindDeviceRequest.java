package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BindDeviceRequest
{
    @NotBlank(message = "user name can not be null")
    @Size(min = 11,max = 11)
    //待绑定的用户名
    private String userName;

    private String token;

    @NotBlank(message = "device num can not be null")
    @Size(min = 11,max = 11)
    //待绑定的设备编号
    private String deviceNum;

    //设备名称
    private String deviceName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserBindDeviceRequest{" +
                "userName='" + userName + '\'' +
                ", deviceNum='" + deviceNum + '\'' +
                ", deviceName='" + deviceName + '\'' +
                '}';
    }
}
