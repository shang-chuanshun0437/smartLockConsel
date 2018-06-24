package com.mutong.smartlock.dao.entity;

import javax.persistence.*;

//用户绑定的设备信息表
@Entity
@Table(name="user_device")
public class UserAttachedDeviceInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "device_num")
    private String deviceNum;

    //用户类型：0 主用户  1 子用户
    @Column(name = "user_type")
    private String userType;

    //用户关联设备的时间
    @Column(name = "associate_time")
    private String associateTime;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "device_mac")
    private String deviceMac;

    //管理员账号
    @Column(name = "main_user")
    private String mainUser;

    //智能锁版本
    @Column(name = "version")
    private String version;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac;
    }

    public String getMainUser() {
        return mainUser;
    }

    public void setMainUser(String mainUser) {
        this.mainUser = mainUser;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
