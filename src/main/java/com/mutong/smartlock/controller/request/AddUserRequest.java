package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddUserRequest
{
    @NotBlank(message = "user name can not be null")
    @Size(min = 11,max = 11)
    private String userName;

    @NotBlank(message = "password can not be null")
    @Size(min = 6,max = 11)
    private String password;

    //终端ID即手机的设备ID
    private String terminalId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
}
