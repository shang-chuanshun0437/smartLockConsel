package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class QueryUserAttachedDeviceRequest
{
    @NotBlank(message = "user name can not be null")
    @Size(min = 11,max = 11)
    //11位手机号
    private String phoneNum;

    @NotBlank(message = "token can not be null")
    private String token;

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
}
