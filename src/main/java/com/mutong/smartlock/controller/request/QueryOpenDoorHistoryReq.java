package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class QueryOpenDoorHistoryReq
{
    @NotBlank(message = "user name can not be null")
    @Size(min = 11,max = 11)
    private String userName;

    @NotBlank(message = "user token can not be null")
    private String token;

    @NotBlank(message = "device num can not be null")
    private String deviceNum;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
