package com.mutong.smartlock.controller.response;

import com.mutong.smartlock.common.Result;

public class BindDeviceResponse
{
    private Result result;

    private String bloothMac;

    private String attachedTime;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getBloothMac() {
        return bloothMac;
    }

    public void setBloothMac(String bloothMac) {
        this.bloothMac = bloothMac;
    }

    public String getAttachedTime() {
        return attachedTime;
    }

    public void setAttachedTime(String attachedTime) {
        this.attachedTime = attachedTime;
    }

}
