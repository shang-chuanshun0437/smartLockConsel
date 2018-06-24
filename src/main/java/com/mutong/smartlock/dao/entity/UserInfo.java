package com.mutong.smartlock.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_info")
public class UserInfo
{
    //用户名：手机号
    @Id
    @Column(name="user_name")
    private String userName;

    //用户密码
    @Column(name="password")
    private String password;

    //创建用户的时间,格式为：20180528
    @Column(name = "create_time")
    private String createTime;

    //墓碑时间
    @Column(name = "tomb_time")
    private String tombTime;

    //用户token
    @Column(name = "token")
    private String token;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTombTime() {
        return tombTime;
    }

    public void setTombTime(String tombTime) {
        this.tombTime = tombTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", tombTime='" + tombTime + '\'' +
                '}';
    }

}
