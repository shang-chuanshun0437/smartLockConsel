package com.mutong.smartlock.controller.response;

import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.service.common.UserAttachedDevice;

public class BindDevice4UserResponse
{
    private Result result;

    private UserAttachedDevice userAttachedDevice;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public UserAttachedDevice getUserAttachedDevice() {
        return userAttachedDevice;
    }

    public void setUserAttachedDevice(UserAttachedDevice userAttachedDevice) {
        this.userAttachedDevice = userAttachedDevice;
    }
}
