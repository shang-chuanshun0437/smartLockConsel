package com.mutong.smartlock.service.exception;

public class UserManageExiception extends RuntimeException
{
    private String msg;

    private String code;

    public UserManageExiception(String code,String msg)
    {
        this.code = code;
        this.msg = msg;
    }
    @Override
    public String toString() {
        return "UserManageExiception{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
