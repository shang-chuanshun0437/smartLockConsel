package com.mutong.smartlock.common;

public class LockException extends RuntimeException
{
    private String msg;

    private String code;

    public LockException(String code,String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public LockException(String code)
    {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "LockException{" +
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
