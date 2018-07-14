package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BindDevice4UserRequest
{
    @NotBlank(message = "user name can not be null")
    @Size(min = 11,max = 11)
    //设备管理员手机号
    private String phoneNum;

    @NotBlank(message = "token can not be null")
    private String token;

    @NotBlank(message = "device num can not be null")
    @Size(min = 11,max = 11)
    //设备编号
    private String deviceNum;

    @NotBlank(message = "device num can not be null")
    @Size(min = 11,max = 11)
    //待绑定的用户手机号
    private String bindPhoneNum;

    //钥匙有效期
    private String validDate;

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
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

    public String getBindPhoneNum() {
        return bindPhoneNum;
    }

    public void setBindPhoneNum(String bindPhoneNum) {
        this.bindPhoneNum = bindPhoneNum;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    @Override
    public String toString() {
        return "BindDevice4UserRequest{" +
                "phoneNum='" + phoneNum + '\'' +
                ", deviceNum='" + deviceNum + '\'' +
                ", bindPhoneNum='" + bindPhoneNum + '\'' +
                ", validDate='" + validDate + '\'' +
                '}';
    }
}
