package com.mutong.smartlock.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class QueryOpenDoorHistoryReq
{
    @NotBlank(message = "phoneNum can not be null")
    @Size(min = 11,max = 11)
    private String phoneNum;

    @NotBlank(message = "user token can not be null")
    private String token;

    @NotBlank(message = "device num can not be null")
    private String deviceNum;

    private String phoneType;

    //查询第几页
    private int page;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "QueryOpenDoorHistoryReq{" +
                "phoneNum='" + phoneNum + '\'' +
                ", token='" + token + '\'' +
                ", deviceNum='" + deviceNum + '\'' +
                ", phoneType='" + phoneType + '\'' +
                ", page=" + page +
                '}';
    }
}
