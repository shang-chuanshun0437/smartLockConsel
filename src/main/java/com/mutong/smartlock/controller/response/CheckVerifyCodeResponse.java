package com.mutong.smartlock.controller.response;

import com.mutong.smartlock.common.Result;

public class CheckVerifyCodeResponse
{
    private Result result;

    //验证码正确后，返回的注册凭证
    private String voucher;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }
}
