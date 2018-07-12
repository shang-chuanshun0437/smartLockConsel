package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ModifyUserNameRequest
{
    @NotBlank(message = "phoneNum can not be null")
    @Size(min = 11,max = 11)
    private String phoneNum;

    @NotBlank(message = "token can not be null")
    private String token;

    @NotBlank(message = "new userName can not be null")
    private String newUserName;

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

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    @Override
    public String toString() {
        return "ModifyUserNameRequest{" +
                "phoneNum='" + phoneNum + '\'' +
                ", newUserName='" + newUserName + '\'' +
                '}';
    }
}
