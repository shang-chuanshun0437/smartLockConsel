package com.mutong.smartlock.common;

public class Result
{
    private String retcode;

    private String retmsg;

    private NamedParmeter[] extendsInfo;

    public Result()
    {
        this.retcode =ErrorCode.SUCCESS;
        this.retmsg = "success";
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public NamedParmeter[] getExtendsInfo() {
        return extendsInfo;
    }

    public void setExtendsInfo(NamedParmeter[] extendsInfo) {
        this.extendsInfo = extendsInfo;
    }
}
