package com.mutong.smartlock.common;

public class Result
{
    private String retcode = "000000";

    private String retmsg;

    private NamedParmeter[] extendsInfo;

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
