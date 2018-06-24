package com.mutong.smartlock.service.common;

public class UserAttachedDevice
{
    private String userName;

    //管理员
    private String mainName;

    private String deviceNum;

    private String deviceName;

    //智能锁蓝牙mac
    private String bloothMac;

    //智能锁版本号
    private String version;

    private String userType;

    //绑定设备的时间
    private String associateTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getBloothMac() {
        return bloothMac;
    }

    public void setBloothMac(String bloothMac) {
        this.bloothMac = bloothMac;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAssociateTime() {
        return associateTime;
    }

    public void setAssociateTime(String associateTime) {
        this.associateTime = associateTime;
    }
}
