package com.mutong.smartlock.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="device_info")
public class DeviceInfo
{
    //设备编号：7位依次增加的数字+4位随机数
    @Id
    @Column(name="device_num")
    private String deviceNum;

    //蓝牙的mac地址
    @Column(name="bluetooth_mac")
    private String bluetoothMac;

    //手机号,设备的所有者，该用户可以操作智能锁的所有功能：添加用户、删除用户
    @Column(name = "user_name")
    private String userName;

    //创建设备的时间,格式为：yyyyMMddHHmm
    @Column(name = "create_time")
    private String createTime;

    //设备名称，由主账户在绑定设备的传入
    @Column(name = "device_name")
    private String deviceName;

    //智能锁版本
    @Column(name = "version")
    private String version;

    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    public String getBluetoothMac() {
        return bluetoothMac;
    }

    public void setBluetoothMac(String bluetoothMac) {
        this.bluetoothMac = bluetoothMac;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "deviceNum=" + deviceNum +
                ", bluetoothMac='" + bluetoothMac + '\'' +
                ", userName='" + userName + '\'' +
                ", deviceTime='" + createTime + '\'' +
                '}';
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
