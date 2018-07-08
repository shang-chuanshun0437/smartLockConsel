package com.mutong.smartlock.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="open_door_history")
public class OpenDoorHistoryInfo
{
    @Id
    @Column(name="id")
    private String id;

    @Column(name="user_name")
    private String userName;

    //创建用户的时间,格式为：20180528
    @Column(name = "open_time")
    private String openTime;

    //用户昵称
    @Column(name = "nick_name")
    private String nickName;

    //设备编号
    @Column(name = "device_num")
    private String deviceNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }
}
