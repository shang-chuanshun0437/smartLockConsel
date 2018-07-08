package com.mutong.smartlock.service.common;

public class OpenDoorHistory
{
    private String userName;
    //开门时间
    private String openTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }
}
