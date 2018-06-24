package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class QueryUserAttachedDeviceRequest
{
    @NotBlank(message = "user name can not be null")
    @Size(min = 11,max = 11)
    //用户名:11位手机号
    private String userName;

    @NotBlank(message = "token can not be null")
    private String token;

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
}
