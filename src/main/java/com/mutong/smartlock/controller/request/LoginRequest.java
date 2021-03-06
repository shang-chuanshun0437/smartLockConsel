package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest
{
    @NotBlank(message = "user name can not be null")
    @Size(min = 11,max = 11)
    private String phoneNum;

    @NotBlank(message = "user password can not be null")
    @Size(min = 8,max = 48)
    private String password;

    private String terminalId;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                ", terminalId='" + terminalId + '\'' +
                '}';
    }
}
