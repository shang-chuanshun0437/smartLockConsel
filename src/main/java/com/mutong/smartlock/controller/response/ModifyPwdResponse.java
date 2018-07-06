package com.mutong.smartlock.controller.response;

import com.mutong.smartlock.common.Result;

public class ModifyPwdResponse
{
    private Result result;

    private String token;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
