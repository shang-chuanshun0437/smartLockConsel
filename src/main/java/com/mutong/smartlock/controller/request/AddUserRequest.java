package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddUserRequest
{
    @NotBlank(message = "user phone can not be null")
    @Size(min = 11,max = 11)
    private String phoneNum;

    @NotBlank(message = "user name can not be null")
    @Size(min = 1,max = 10)
    private String userName;

    @NotBlank(message = "password can not be null")
    @Size(min = 8,max = 48)
    private String password;

    @NotBlank(message = "voucher can not be null")
    @Size(min = 6,max = 6)
    private String voucher;

    //终端ID即手机的设备ID
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    @Override
    public String toString() {
        return "AddUserRequest{" +
                "phoneNum='" + phoneNum + '\'' +
                ", userName='" + userName + '\'' +
                ", voucher='" + voucher + '\'' +
                ", terminalId='" + terminalId + '\'' +
                '}';
    }
}
