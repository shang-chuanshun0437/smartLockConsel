package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ModifyPwdRequest
{
    @NotBlank(message = "user name can not be null")
    @Size(min = 11,max = 11)
    private String userName;

    @NotBlank(message = "password can not be null")
    private String oldpassword;

    @NotBlank(message = "new password can not be null")
    private String newpassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
