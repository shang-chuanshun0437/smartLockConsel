package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CheckVerifyCodeRequest
{
    @NotBlank(message = "user phone can not be null")
    @Size(min = 11,max = 11)
    private String phoneNum;

    @NotBlank(message = "verify code can not be null")
    @Size(min = 6,max = 6)
    private String verifyCode;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "CheckVerifyCodeRequest{" +
                "phoneNum='" + phoneNum + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
