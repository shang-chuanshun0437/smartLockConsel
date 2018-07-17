package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DeleteDeviceOfUserRequest
{
    @NotBlank(message = "user name can not be null")
    @Size(min = 11,max = 11)
    private String phoneNum;

    @NotBlank(message = "token can not be null")
    private String token;

    @NotBlank(message = "user name can not be null")
    @Size(min = 11,max = 11)
    //待删除的手机号
    private String deletePhoneNum;

    @NotBlank(message = "device num can not be null")
    @Size(min = 11,max = 11)
    //待删除的设备编号
    private String deviceNum;

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

    public String getDeletePhoneNum() {
        return deletePhoneNum;
    }

    public void setDeletePhoneNum(String deletePhoneNum) {
        this.deletePhoneNum = deletePhoneNum;
    }

    @Override
    public String toString() {
        return "DeleteDeviceOfUserRequest{" +
                "phoneNum='" + phoneNum + '\'' +
                ", deletePhoneNum='" + deletePhoneNum + '\'' +
                ", deviceNum='" + deviceNum + '\'' +
                '}';
    }
}
